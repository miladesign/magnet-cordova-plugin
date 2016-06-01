var magnet = {
    Position: {
        TOP: 100,
        BOTTOM: 101
    },
    	MagnetInitialize: function(successCallback, failureCallback) {
		var self = this;	
        cordova.exec(
            function (result) {
				if (typeof result == "string") {
					if (result == "onMagnetInterstitialAdReceived") {					
						if (self.onInterstitialAdReceived)
							self.onInterstitialAdReceived();
					}
					else if (result == "onMagnetInterstitialAdFailed") {
						if (self.onInterstitialAdFailed)
							self.onInterstitialAdFailed();
					}
				}
				else {
					//var event = result["event"];
					//var location = result["message"];				
					//if (event == "onXXX") {
					//	if (self.onXXX)
					//		self.onXXX(location);
					//}
				}			
			}, 
			function (error) {
			},
            successCallback,
            failureCallback,
            'MdMagnet',
            'initialize',
            []
        ); 
    },
	
    MagnetSetTestMode: function (testMode, successCallback, failureCallback) {
        cordova.exec(
            successCallback,
            failureCallback,
            'MdMagnet',
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
            'MdMagnet',
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
            'MdMagnet',
            'showInterstitial',
            []
        );
    },
	
    MagnetShowBanner: function (addUnitId, position, successCallback, failureCallback) {
        cordova.exec(
            successCallback,
            failureCallback,
            'MdMagnet',
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
            'MdMagnet',
            'removeBanner',
            []
        );
    }
};

module.exports = magnet;
