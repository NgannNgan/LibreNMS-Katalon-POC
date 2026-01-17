import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import com.kms.katalon.core.webui.driver.DriverFactory

import org.openqa.selenium.JavascriptExecutor
 
// Navigate to Overview

WebUI.waitForPageLoad(20)
WebUI.navigateToUrl('https://demo.librenms.org/overview')
WebUI.delay(5)
 
// SYSTEM-LEVEL VALIDATION

JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getWebDriver()
 

assert js.executeScript("return document.readyState") == 'complete'

assert js.executeScript("return window.onerror == null") == true
assert js.executeScript(

	"return (window.jQuery && jQuery.active === 0)") == true
 
String pageText = js.executeScript(

	"return document.body.innerText").toString().trim()
 
assert pageText.length() > 50 : '❌ Dashboard rendered empty content'
 

// Capture

WebUI.takeScreenshot()
WebUI.closeBrowser()

 