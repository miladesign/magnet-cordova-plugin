package ir.magnet.cordova;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import ir.magnet.sdk.MagnetAdLoadListener;
import ir.magnet.sdk.MagnetInterstitialAd;
import ir.magnet.sdk.MagnetMobileBannerAd;
import ir.magnet.sdk.MagnetSDK;
import ir.magnet.sdk.MagnetSettings;
import java.lang.reflect.Method;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MdMagnet extends CordovaPlugin
{
  protected MagnetMobileBannerAd bannerAd;
  protected MagnetInterstitialAd interstitialAd;
  public static Activity activity;
  private FrameLayout bannerFrame;
  private static final int POSITION_TOP = 100;
  private static final int POSITION_BOTTOM = 101;
  private static final String str_onInterstitialAdReceived = "onMagnetInterstitialAdReceived";
  private static final String str_onInterstitialAdFailed = "onMagnetInterstitialAdFailed";
  public static final String TAG = "MilaDesign";

  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
	  Log.d(TAG, "Plugin execute called with action: " + paramString);
    activity = this.cordova.getActivity();
    if (activity == null)
      throw new IllegalArgumentException("No activity specified");
    if ("initialize".equals(paramString))
      magnetInitialize(paramJSONArray, paramCallbackContext);
    if ("setTestMode".equals(paramString))
      setTestMode(paramJSONArray, paramCallbackContext);
    if ("ShowBanner".equals(paramString))
      showBanner(paramJSONArray, paramCallbackContext);
    if ("loadInterstitial".equals(paramString))
      loadInterstitial(paramJSONArray, paramCallbackContext);
    if ("showInterstitial".equals(paramString))
      showInterstitial(paramJSONArray, paramCallbackContext);
    if ("removeBanner".equals(paramString))
      removeBanner(paramJSONArray, paramCallbackContext);
    return true;
  }

  public void magnetInitialize(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    MagnetSDK.initialize(this.cordova.getActivity());
    paramCallbackContext.success();
    Log.d(TAG, "initialising plugin");
  }

  public void setTestMode(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    JSONObject localJSONObject = null;
    Boolean localBoolean = Boolean.valueOf(true);
    if (paramJSONArray != null)
      localJSONObject = paramJSONArray.getJSONObject(0);
    if (localJSONObject != null)
      localBoolean = Boolean.valueOf(localJSONObject.getBoolean("TEST_MODE"));
    MagnetSDK.getSettings().setTestMode(localBoolean.booleanValue());
    paramCallbackContext.success();
  }

  public void showBanner(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    JSONObject localJSONObject = null;
    String str = null;
    int i = 101;
    if (paramJSONArray != null)
      localJSONObject = paramJSONArray.getJSONObject(0);
    if (localJSONObject != null)
    {
      str = localJSONObject.getString("AD_UNIT_ID");
      try
      {
        i = localJSONObject.getInt("BANNER_POSITION");
      }
      catch (JSONException localJSONException)
      {
      }
    }
    removeBanner(null, null);
    this.bannerAd = MagnetMobileBannerAd.create(this.cordova.getActivity());
    this.bannerAd.load(str, getBannerView(i));
    paramCallbackContext.success();
  }

  private ViewGroup getBannerView(int paramInt)
  {
    this.bannerFrame = new FrameLayout(this.cordova.getActivity());
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    switch (paramInt)
    {
    case 100:
      localLayoutParams.gravity = 49;
      break;
    case 101:
      localLayoutParams.gravity = 81;
    }
    this.bannerFrame.setLayoutParams(localLayoutParams);
    activity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        ((ViewGroup)MdMagnet.this.getParentGroup().getParent()).addView(MdMagnet.this.bannerFrame, 1);
      }
    });
    return this.bannerFrame;
  }

  private ViewGroup getParentGroup()
  {
    try
    {
      Method localMethod1 = this.webView.getClass().getMethod("getView", new Class[0]);
      return (ViewGroup)localMethod1.invoke(this.webView, new Object[0]);
    }
    catch (Exception localException1)
    {
      try
      {
        Method localMethod2 = this.webView.getClass().getMethod("getParent", new Class[0]);
        return (ViewGroup)localMethod2.invoke(this.webView, new Object[0]);
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    return null;
  }

  public void loadInterstitial(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    JSONObject localJSONObject = null;
    String str = null;
    if (paramJSONArray != null)
      localJSONObject = paramJSONArray.getJSONObject(0);
    if (localJSONObject != null)
      str = localJSONObject.getString("AD_UNIT_ID");
    this.interstitialAd = MagnetInterstitialAd.create(this.cordova.getActivity());
    this.interstitialAd.setAdLoadListener(new MagnetAdLoadListener()
    {
      public void onPreload(int paramAnonymousInt, String paramAnonymousString)
      {
      }

      public void onReceive()
      {
        MdMagnet.this.fireEvent("onMagnetInterstitialAdReceived", "onReceive");
      }

      public void onFail(int paramAnonymousInt, String paramAnonymousString)
      {
        MdMagnet.this.fireEvent("onMagnetInterstitialAdFailed", "onFail");
      }
    });
    this.interstitialAd.load(str);
    paramCallbackContext.success();
  }

  public void showInterstitial(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
    throws JSONException
  {
    this.interstitialAd.show();
  }

  void fireEvent(String paramString1, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("data", paramString2);
      String str1 = localJSONObject.toString();
      String str2 = "javascript:cordova.fireDocumentEvent(\"" + paramString1 + "\"," + str1 + ");";
      Method localMethod = this.webView.getClass().getMethod("loadUrl", new Class[] { String.class });
      localMethod.invoke(this.webView, new Object[] { str2 });
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void onDestroy()
  {
    removeBanner(null, null);
    this.bannerAd = null;
    this.interstitialAd = null;
    activity = null;
    super.onDestroy();
  }

  private void removeBanner(JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    if ((this.bannerAd == null) || (this.bannerFrame == null))
      return;
    if (activity != null)
    {
      activity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          ViewGroup localViewGroup1 = MdMagnet.this.getParentGroup();
          if ((localViewGroup1 != null) && ((localViewGroup1 instanceof ViewGroup)))
          {
            ViewGroup localViewGroup2 = (ViewGroup)localViewGroup1.getParent();
            if (localViewGroup2.getChildAt(1) != null)
              ((ViewGroup)localViewGroup1.getParent()).removeViewAt(1);
          }
        }
      });
      if (paramCallbackContext != null)
        paramCallbackContext.success();
    }
  }
}