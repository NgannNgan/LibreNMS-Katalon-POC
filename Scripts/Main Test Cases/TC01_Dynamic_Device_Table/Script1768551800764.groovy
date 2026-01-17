import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
 
TestObject devicesMenu = new TestObject().addProperty(
	'xpath',
	ConditionType.EQUALS,
	"//a[contains(@href,'devices')]"

)
 
WebUI.waitForElementClickable(devicesMenu, 30)
WebUI.click(devicesMenu)
WebUI.waitForPageLoad(30)
 
Object loaded = WebUI.executeJavaScript(
	"return document.body.innerText.toLowerCase().includes('device');",
	null
)
 
assert loaded == true : "Devices page not loaded"
 
WebUI.closeBrowser()
 