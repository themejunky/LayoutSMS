# LayoutSMS

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:26.1.0'
    compile 'com.android.support.constraint:constraint-layout:1.1.0'
    compile 'com.github.themejunky:LayoutSMS:1.0.7'
    //ads
    compile 'com.facebook.android:audience-network-sdk:4.27.1'
    compile "com.google.android.gms:play-services-ads:11.0.5"
    compile "com.appnext.sdk:native-ads2:2+"
    compile "com.appnext.sdk:ads:2+"
}







allprojects {
    repositories {
        google()
        jcenter()
        maven { url "http://dl.appnext.com/" }
        maven { url 'https://jitpack.io' }
    }
}



        ManagerOnboarding.setStatus(this, true);
