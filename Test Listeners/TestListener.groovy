import com.kms.katalon.core.annotation.*
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


class TestListener {
    @BeforeTestCase
	def beforeTestCase(TestCaseContext context) {
        WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl('https://demo.librenms.org')
		WebUI.setText(findTestObject('Login/input_username'), 'demo')
		WebUI.setText(findTestObject('Login/input_password'), 'demouser')
		WebUI.click(findTestObject('Login/button_login'))
		WebUI.verifyElementPresent(findTestObject('Menu/menu_devices'), 10)
}

	@AfterTestCase
    def takeScreenshot(TestCaseContext testCaseContext) {
        String status = testCaseContext.getTestCaseStatus()
        String tcName = testCaseContext.getTestCaseId().replaceAll('[^a-zA-Z0-9]', '_')
 
        String fileName = tcName + "_" + status + ".png"
        WebUI.takeScreenshot("Reports/" + fileName)
    }

}