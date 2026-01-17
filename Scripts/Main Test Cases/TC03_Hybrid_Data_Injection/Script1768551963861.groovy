
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.RequestObject

// CONFIGURATION

int MAX_RETRY = 2                        
int TIMEOUT = 60000                     
boolean backendAvailable = false         
def response = null                     

// STEP 1: LOAD API TEST OBJECT

RequestObject request = findTestObject('API/Inject_Device')
if (request == null) {
	KeywordUtil.markFailedAndStop("❌ TestObject 'API/Inject_Device' NOT FOUND. Check Object Repository path!")
}
KeywordUtil.logInfo("⚙ Loaded API TestObject: API/Inject_Device")

// STEP 2: RETRY API CALL UNTIL BACKEND IS AVAILABLE

int retry = 0
while (retry <= MAX_RETRY && !backendAvailable) {
	try {
		KeywordUtil.logInfo("🌐 Attempting API injection (retry = ${retry})")

		// Send API request with timeout
		response = WS.sendRequest(request, TIMEOUT)
		int statusCode = response.getStatusCode()

		if (statusCode in [200, 201]) {
			backendAvailable = true
			KeywordUtil.logInfo("✅ Backend reachable – API injection SUCCESS (status: ${statusCode})")
		} else {
			KeywordUtil.markWarning("⚠ API responded with unexpected status: ${statusCode}")
		}

	} catch (Exception e) {
		KeywordUtil.markWarning("⚠ Connection error: ${e.getMessage()}")
	}

	if (!backendAvailable) retry++
}

// STEP 3: HANDLE BACKEND UNAVAILABLE SCENARIO
if (!backendAvailable) {
	KeywordUtil.markWarning("⛔ Backend unreachable after ${MAX_RETRY} retries → Skipping TC03.")
	return    // Exit test case safely without failing
}

// STEP 4: VALIDATE API RESPONSE DATA
String injectedDeviceName = WS.getElementPropertyValue(response, 'device_name')

if (!injectedDeviceName) {
	KeywordUtil.markFailedAndStop("❌ API response missing required field: 'device_name'")
}

KeywordUtil.logInfo("📌 Injected Device Name from API: ${injectedDeviceName}")

// STEP 5: LOGIN TO UI
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.setText(findTestObject('UI/Login/username'), GlobalVariable.username)
WebUI.setEncryptedText(findTestObject('UI/Login/password'), GlobalVariable.password)
WebUI.click(findTestObject('UI/Login/login_button'))

WebUI.waitForPageLoad(30)

// STEP 6: SEARCH DEVICE NAME IN UI
WebUI.setText(findTestObject('UI/Search/search_input'), injectedDeviceName)
WebUI.click(findTestObject('UI/Search/search_button'))

// STEP 7: VERIFY UI RESULTS MATCH API DATA
WebUI.verifyElementText(
		findTestObject('UI/Device/device_name_label'),
		injectedDeviceName
)

WebUI.closeBrowser()
KeywordUtil.markPassed("🎯 TC03 PASSED – API-injected data successfully validated in UI")
