module.exports = {
	Position: {
        TOP: 100,
        BOTTOM: 101
    },
	MagnetInitialize: function () {
		var self = this;
        cordova.exec(
			function (success) {
                console.log('Initialize successed.');
            },
            function (error) {
                console.log('Initialize failed.');
            },
			'MagnetCordovaPlugin',
            'initialize',
            []
        );
    },
	MagnetSetTestMode: function (testMode) {
        cordova.exec(
            null,
            null,
            'MagnetCordovaPlugin',
            'setTestMode',
            [
                {'TEST_MODE': testMode}
            ]
        );
    },
	MagnetLoadInterstitial: function (addUnitId) {
		var self = this;
        cordova.exec(
			function (result) {
				if (typeof result == "string") {
					if (result == "onMagnetInterstitialAdReceived") {
						if (self.onMagnetInterstitialAdReceived)
							self.onMagnetInterstitialAdReceived();
					}
					else if (result == "onMagnetInterstitialAdFailed") {
						if (self.onMagnetInterstitialAdFailed)
							self.onMagnetInterstitialAdFailed();
					}
				}
				console.log(result);
            },
            null,
            'MagnetCordovaPlugin',
            'loadInterstitial',
			[
				{'AD_UNIT_ID': addUnitId}
            ]
        );
    },
    MagnetShowInterstitial: function () {
        cordova.exec(
            null,
            null,
            'MagnetCordovaPlugin',
            'showInterstitial',
            []
        );
    },
	MagnetLoadRewardAd: function (addUnitId) {
		var self = this;
        cordova.exec(
            function (result) {
				if (typeof result == "string") {
					if (result == "onMagnetRewardAdReceived") {
						if (self.onMagnetRewardAdReceived)
							self.onMagnetRewardAdReceived();
					}
					else if (result == "onMagnetRewardAdFailed") {
						if (self.onMagnetRewardAdFailed)
							self.onMagnetRewardAdFailed();
					}
				}
				console.log(result);
            },
            null,
            'MagnetCordovaPlugin',
            'loadRewardAd',
			[
				{'AD_UNIT_ID': addUnitId}
            ]
        );
    },
    MagnetShowRewardAd: function () {
		var self = this;
        cordova.exec(
            function (result) {
				if (typeof result == "string") {
					if (result == "onRewardSuccessful") {
						if (self.onRewardSuccessful)
							self.onRewardSuccessful();
					}	
					else if (result == "onRewardFail") {
						if (self.onRewardFail)
							self.onRewardFail();
					}
				}
				console.log(result);
            },
            null,
            'MagnetCordovaPlugin',
            'showRewardAd',
            []
        );
    },
    MagnetShowBanner: function (addUnitId, position) {
        cordova.exec(
            null,
            null,
            'MagnetCordovaPlugin',
            'ShowBanner',
            [
                {'AD_UNIT_ID': addUnitId, 'BANNER_POSITION': position}
            ]
        );
    },
    MagnetRemoveBanner: function () {
        cordova.exec(
            null,
            null,
            'MagnetCordovaPlugin',
            'removeBanner',
            []
        );
    },
	onMagnetInterstitialAdReceived : null,
	onMagnetInterstitialAdFailed : null,
	onMagnetRewardAdReceived : null,
	onMagnetRewardAdFailed : null,
	onRewardSuccessful : null,
	onRewardFail : null
};