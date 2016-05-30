Magnet Cordova plugin
====================
Magnet plugin for Cordova and Phonegap.<br/>


- project home: [https://github.com/MagnetAdServices/magnet-cordova-plugin](https://github.com/MagnetAdServices/magnet-cordova-plugin)<br/>
- Magnet android SDK 4.0.5<br/>
- Cordova version >3.0<br/>

### 1.install magnet cordova plugin

download the plugin ,then install with local location

    cordova plugin add c:\magnet_cordova_plugin 

### 2.init magnet cordova plugin
init plugin after deviceready event <br />

    magnet.MagnetInitialize(function(){alert('init success');}, function(){alert('init fail');});
    
### 3.enable/disable test mode
after init, set test mode.

    magnet.MagnetSetTestMode(true, null, null);

set test mode to false on release time.<br />
### 4.show banner 

you can show the banner at the TOP/BOTTOM of your app.
magnet.Position holds  positions const .

    magnet.MagnetShowBanner('Your adunit id', magnet.Position.BOTTOM, null, null); 


###  5.show interstitial 
load Interstitial ,and then show it in onInterstitialReceived function or show it when your game over.

```
function loadInterstitial(){
	document.addEventListener(magnet.Event.onInterstitialAdReceived, onInterstitialReceived, false);
	magnet.MagnetLoadInterstitial('Your adunit id', null, null);
}

function showInterstitial(){
	magnet.MagnetShowInterstitial(null, null);
}

function onInterstitialReceived(){
	alert('Interstitial is ready');
	// or call showInterstitial()
}
```
