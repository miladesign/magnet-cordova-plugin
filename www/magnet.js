var magnet = {
    Position: {
        TOP: 100,
        BOTTOM: 101
    },
    Event: {
        onInterstitialAdReceived :"onMagnetInterstitialAdReceived",
        onInterstitialAdFailed :"onMagnetInterstitialAdFailed",
    },
    
	MagnetInitialize: function (successCallback, failureCallback) {
        cordova.exec(
            successCallback,
            failureCallback,
            'MagnetCordovaPlugin',
            'initialize',
            []
        );
    },
    MagnetSetTestMode: function (testMode, successCallback, failureCallback) {
        cordova.exec(
            successCallback,
            failureCallback,
            'MagnetCordovaPlugin',
            'setTestMode',
            [
                {'TEST_MODE': testMode}
            ]
        );
    },
	MagnetLoadInterstitial: function (addUnitId, successCallback, failureCallback) {
        cordova.exec(
            successCallback,
            failureCallback,
            'MagnetCordovaPlugin',
            'loadInterstitial',
			[
				{'AD_UNIT_ID': addUnitId}
            ]
        );
    },
    MagnetShowInterstitial: function (successCallback, failureCallback) {
        cordova.exec(
            successCallback,
            failureCallback,
            'MagnetCordovaPlugin',
            'showInterstitial',
            []
        );
    },
    MagnetShowBanner: function (addUnitId, position, successCallback, failureCallback) {
        cordova.exec(
            successCallback,
            failureCallback,
            'MagnetCordovaPlugin',
            'ShowBanner',
            [
                {'AD_UNIT_ID': addUnitId, 'BANNER_POSITION': position}
            ]
        );
    },
    MagnetRemoveBanner: function (successCallback, failureCallback) {
        cordova.exec(
            successCallback,
            failureCallback,
            'MagnetCordovaPlugin',
            'removeBanner',
            []
        );
    }
};
module.exports = magnet;
