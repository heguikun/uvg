# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-ignorewarnings    ###屏蔽警告
#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类 start---------------------------------

-keep class com.uvgouapp.model.circle.**{*;}
-keep class com.uvgouapp.model.other.**{*;}
-keep class com.uvgouapp.model.shop.**{*;}
-keep class com.uvgouapp.model.show.**{*;}
-keep class com.uvgouapp.model.user.**{*;}

#----------------------------------1.实体类  end---------------------------------------

#---------------------------------2.第三方包 start-------------------------------

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}
#这句非常重要，主要是过滤掉 com.demo.demo.bean包下的所有.class文件不进行混淆编译,com.demo.demo是你的包名
-keep class com.uvgouapp.model.circle.**{*;}
-keep class com.uvgouapp.model.other.**{*;}
-keep class com.uvgouapp.model.shop.**{*;}
-keep class com.uvgouapp.model.show.**{*;}
-keep class com.uvgouapp.model.user.**{*;}
##---------------End: proguard configuration for Gson  ----------

#############AndroidUtilCode#############
-keep class com.blankj.utilcode.util.* {*;}

#############StatusBarUtil#############
-keep class com.jaeger.library.StatusBarUtil.*{*;}

#############AVLoadingIndicatorView#############
-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }

################RxJava and RxAndroid###############
-dontwarn sun.misc.**
-dontwarn javax.annotation.**
-dontwarn org.mockito.**
-dontwarn org.junit.**
-dontwarn org.robolectric.**

-keep class io.reactivex.** { *; }
-keep interface io.reactivex.** { *; }

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-dontwarn okio.**
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn io.reactivex.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class sun.misc.Unsafe { *; }

-dontwarn java.lang.invoke.*

-keep class io.reactivex.schedulers.Schedulers {
    public static <methods>;
}
-keep class io.reactivex.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class io.reactivex.schedulers.TestScheduler {
    public <methods>;
}
-keep class io.reactivex.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class io.reactivex.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class io.reactivex.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

-keepclassmembers class io.reactivex.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    io.reactivex.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class io.reactivex.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    io.reactivex.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontwarn io.reactivex.internal.util.unsafe.**

################RxLifeCycle#################
-keep class com.trello.rxlifecycle2.** { *; }
-keep interface com.trello.rxlifecycle2.** { *; }

###############okhttp#########
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

############okio##############
-dontwarn okio.**
-keep class okio.**{*;}

##############Glide#############
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

########## for DexGuard only############
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule

#######butterknife#########
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

##############RxPermissions##############
-keep class com.tbruyelle.rxpermissions2.** { *; }
-keep interface com.tbruyelle.rxpermissions2.** { *; }

##############EventBus##############
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

##################Bugly##################
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

##################ZXing##################
-dontwarn com.google.zxing.*

#################BaseRecyclerViewAdapterHelper##################
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers  class **$** extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(...);
}

####################Banner#####################
-keep class com.youth.banner.** {*;}

####################定位#######################
-keep class com.amap.api.location.**{*;}
-keep class com.loc.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

# ####################搜索#####################
-keep class com.amap.api.services.**{*;}

#####################umeng###################
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class com.woodare.ufo.R$*{
public static final int *;
}

##############Wechatshare##########
-dontwarn com.tencent.mm.**
-keep class com.tencent.mm.**{*;}

#########################Alipay#####################
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
-keep class com.alipay.sdk.app.H5PayCallback {
    <fields>;
    <methods>;
}
-keep class com.alipay.android.phone.mrpc.core.** { *; }
-keep class com.alipay.apmobilesecuritysdk.** { *; }
-keep class com.alipay.mobile.framework.service.annotation.** { *; }
-keep class com.alipay.mobilesecuritysdk.face.** { *; }
-keep class com.alipay.tscenter.biz.rpc.** { *; }
-keep class org.json.alipay.** { *; }
-keep class com.alipay.tscenter.** { *; }
-keep class com.ta.utdid2.** { *;}
-keep class com.ut.device.** { *;}
-dontwarn android.net.**
-keep class android.net.SSLCertificateSocketFactory{*;}

# ------------------ Keep LineNumbers and properties ---------------- #
 -keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*,EnclosingMethod
#################### Addidional for x5.sdk classes for apps
 -keep class com.tencent.smtt.export.external.**{
     *;
 }

 -keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {
 	*;
 }

 -keep class com.tencent.smtt.sdk.CacheManager {
 	public *;
 }

 -keep class com.tencent.smtt.sdk.CookieManager {
 	public *;
 }

 -keep class com.tencent.smtt.sdk.WebHistoryItem {
 	public *;
 }

 -keep class com.tencent.smtt.sdk.WebViewDatabase {
 	public *;
 }

 -keep class com.tencent.smtt.sdk.WebBackForwardList {
 	public *;
 }

 -keep public class com.tencent.smtt.sdk.WebView {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebView$HitTestResult {
 	public static final <fields>;
 	public java.lang.String getExtra();
 	public int getType();
 }

 -keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebView$PictureListener {
 	public <fields>;
 	public <methods>;
 }


 -keepattributes InnerClasses

 -keep public enum com.tencent.smtt.sdk.WebSettings$** {
     *;
 }

 -keep public enum com.tencent.smtt.sdk.QbSdk$** {
     *;
 }

 -keep public class com.tencent.smtt.sdk.WebSettings {
     public *;
 }


 -keepattributes Signature
 -keep public class com.tencent.smtt.sdk.ValueCallback {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebViewClient {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.DownloadListener {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebChromeClient {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {
 	public <fields>;
 	public <methods>;
 }

 -keep class com.tencent.smtt.sdk.SystemWebChromeClient{
 	public *;
 }
 # 1. extension interfaces should be apparent
 -keep public class com.tencent.smtt.export.external.extension.interfaces.* {
 	public protected *;
 }

 # 2. interfaces should be apparent
 -keep public class com.tencent.smtt.export.external.interfaces.* {
 	public protected *;
 }

 -keep public class com.tencent.smtt.sdk.WebViewCallbackClient {
 	public protected *;
 }

 -keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebIconDatabase {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.WebStorage {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.DownloadListener {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.QbSdk {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {
 	public <fields>;
 	public <methods>;
 }
 -keep public class com.tencent.smtt.sdk.CookieSyncManager {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.Tbs* {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.utils.LogFileUtils {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.utils.TbsLog {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.utils.TbsLogClient {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.CookieSyncManager {
 	public <fields>;
 	public <methods>;
 }

 # Added for game demos
 -keep public class com.tencent.smtt.sdk.TBSGamePlayer {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {
 	public <fields>;
 	public <methods>;
 }

 -keep public class com.tencent.smtt.utils.Apn {
 	public <fields>;
 	public <methods>;
 }
 -keep class com.tencent.smtt.** {
 	*;
 }
 # end

#----------------------------------2.第三方包 end---------------------------------------

#---------------------------------3.与js互相调用的类 start------------------------
-keep class com.uvgouapp.presenter.other.WebViewPresenter.**{*;}
#---------------------------------3.与js互相调用的类  end----------------------------------------

#---------------------------------4.反射相关的类和方法-----------------------

#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------