package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class soundmanager extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.soundmanager");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.soundmanager.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.MediaPlayerWrapper _backgroundmusic = null;
public anywheresoftware.b4a.objects.MediaPlayerWrapper _lineclearsound = null;
public b4a.example.gamemanager _gamemngr = null;
public b4a.example.settingsmanager _settingsmgr = null;
public float _music_volume = 0f;
public float _sfx_volume = 0f;
public boolean _ismusicenabled = false;
public boolean _aresoundeffectsenabled = false;
public boolean _isinitializedflag = false;
public boolean _isaudioloadedstate = false;
public String _music_file = "";
public String _line_clear_file = "";
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _applysettingsimmediately() throws Exception{
 //BA.debugLineNum = 184;BA.debugLine="Public Sub ApplySettingsImmediately";
 //BA.debugLineNum = 185;BA.debugLine="Try";
try { //BA.debugLineNum = 187;BA.debugLine="If isMusicEnabled Then";
if (_ismusicenabled) { 
 //BA.debugLineNum = 188;BA.debugLine="StartMusic";
_startmusic();
 }else {
 //BA.debugLineNum = 190;BA.debugLine="StopMusic";
_stopmusic();
 };
 //BA.debugLineNum = 194;BA.debugLine="SaveSettingsToDatabase";
_savesettingstodatabase();
 } 
       catch (Exception e9) {
			ba.setLastException(e9); //BA.debugLineNum = 196;BA.debugLine="Log(\"Error applying settings: \" & LastException.";
__c.LogImpl("818087948","Error applying settings: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 198;BA.debugLine="End Sub";
return "";
}
public String  _backgroundmusic_complete() throws Exception{
 //BA.debugLineNum = 249;BA.debugLine="Sub backgroundMusic_Complete";
 //BA.debugLineNum = 250;BA.debugLine="If isMusicEnabled Then";
if (_ismusicenabled) { 
 //BA.debugLineNum = 251;BA.debugLine="Try";
try { //BA.debugLineNum = 252;BA.debugLine="backgroundMusic.Play";
_backgroundmusic.Play();
 } 
       catch (Exception e5) {
			ba.setLastException(e5); //BA.debugLineNum = 254;BA.debugLine="Log(\"Error restarting music: \" & LastException.";
__c.LogImpl("818350085","Error restarting music: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 };
 //BA.debugLineNum = 257;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private backgroundMusic As MediaPlayer";
_backgroundmusic = new anywheresoftware.b4a.objects.MediaPlayerWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private lineClearSound As MediaPlayer";
_lineclearsound = new anywheresoftware.b4a.objects.MediaPlayerWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Private gamemngr As GameManager";
_gamemngr = new b4a.example.gamemanager();
 //BA.debugLineNum = 11;BA.debugLine="Private settingsMgr As SettingsManager";
_settingsmgr = new b4a.example.settingsmanager();
 //BA.debugLineNum = 14;BA.debugLine="Private Const MUSIC_VOLUME As Float = 0.5";
_music_volume = (float) (0.5);
 //BA.debugLineNum = 15;BA.debugLine="Private Const SFX_VOLUME As Float = 0.8";
_sfx_volume = (float) (0.8);
 //BA.debugLineNum = 18;BA.debugLine="Private isMusicEnabled As Boolean = True";
_ismusicenabled = __c.True;
 //BA.debugLineNum = 19;BA.debugLine="Private areSoundEffectsEnabled As Boolean = True";
_aresoundeffectsenabled = __c.True;
 //BA.debugLineNum = 22;BA.debugLine="Private isInitializedFlag As Boolean = False";
_isinitializedflag = __c.False;
 //BA.debugLineNum = 23;BA.debugLine="Private IsAudioLoadedState As Boolean = False";
_isaudioloadedstate = __c.False;
 //BA.debugLineNum = 25;BA.debugLine="Private Const MUSIC_FILE As String = \"tetris_musi";
_music_file = "tetris_music.mp3";
 //BA.debugLineNum = 26;BA.debugLine="Private Const LINE_CLEAR_FILE As String = \"line_c";
_line_clear_file = "line_clear.mp3";
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return "";
}
public boolean  _getbgmenabled() throws Exception{
 //BA.debugLineNum = 176;BA.debugLine="Public Sub GetBGMEnabled As Boolean";
 //BA.debugLineNum = 177;BA.debugLine="Return isMusicEnabled";
if (true) return _ismusicenabled;
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return false;
}
public boolean  _getsfxenabled() throws Exception{
 //BA.debugLineNum = 180;BA.debugLine="Public Sub GetSFXEnabled As Boolean";
 //BA.debugLineNum = 181;BA.debugLine="Return areSoundEffectsEnabled";
if (true) return _aresoundeffectsenabled;
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return false;
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 29;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 30;BA.debugLine="If isInitializedFlag Then";
if (_isinitializedflag) { 
 //BA.debugLineNum = 31;BA.debugLine="Log(\"SoundManager already initialized, skipping.";
__c.LogImpl("817235970","SoundManager already initialized, skipping...",0);
 //BA.debugLineNum = 32;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 35;BA.debugLine="backgroundMusic.Initialize2(\"backgroundMusic\")";
_backgroundmusic.Initialize2(ba,"backgroundMusic");
 //BA.debugLineNum = 36;BA.debugLine="lineClearSound.Initialize2(\"lineClearSound\")";
_lineclearsound.Initialize2(ba,"lineClearSound");
 //BA.debugLineNum = 39;BA.debugLine="settingsMgr.Initialize";
_settingsmgr._initialize /*String*/ (ba);
 //BA.debugLineNum = 42;BA.debugLine="LoadSettingsFromDatabase";
_loadsettingsfromdatabase();
 //BA.debugLineNum = 44;BA.debugLine="isInitializedFlag = True";
_isinitializedflag = __c.True;
 //BA.debugLineNum = 45;BA.debugLine="Log(\"SoundManager initialized successfully\")";
__c.LogImpl("817235984","SoundManager initialized successfully",0);
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public boolean  _isaudioloaded() throws Exception{
 //BA.debugLineNum = 242;BA.debugLine="Public Sub IsAudioLoaded As Boolean";
 //BA.debugLineNum = 243;BA.debugLine="Return backgroundMusic.IsInitialized And lineClea";
if (true) return _backgroundmusic.IsInitialized() && _lineclearsound.IsInitialized();
 //BA.debugLineNum = 244;BA.debugLine="End Sub";
return false;
}
public boolean  _isinitialized() throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Public Sub IsInitialized As Boolean";
 //BA.debugLineNum = 49;BA.debugLine="Return isInitializedFlag";
if (true) return _isinitializedflag;
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
return false;
}
public String  _lineclearsound_complete() throws Exception{
 //BA.debugLineNum = 259;BA.debugLine="Sub lineClearSound_Complete";
 //BA.debugLineNum = 260;BA.debugLine="Try";
try { //BA.debugLineNum = 261;BA.debugLine="lineClearSound.Position = 0";
_lineclearsound.setPosition((int) (0));
 } 
       catch (Exception e4) {
			ba.setLastException(e4); //BA.debugLineNum = 263;BA.debugLine="Log(\"Error resetting sound: \" & LastException.Me";
__c.LogImpl("818415620","Error resetting sound: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 265;BA.debugLine="End Sub";
return "";
}
public String  _loadaudio() throws Exception{
 //BA.debugLineNum = 83;BA.debugLine="Public Sub LoadAudio";
 //BA.debugLineNum = 84;BA.debugLine="If IsAudioLoaded Then";
if (_isaudioloaded()) { 
 //BA.debugLineNum = 85;BA.debugLine="Log(\"Audio already loaded, skipping...\")";
__c.LogImpl("817498114","Audio already loaded, skipping...",0);
 //BA.debugLineNum = 86;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 89;BA.debugLine="Try";
try { //BA.debugLineNum = 90;BA.debugLine="backgroundMusic.Load(File.DirAssets, MUSIC_FILE)";
_backgroundmusic.Load(__c.File.getDirAssets(),_music_file);
 //BA.debugLineNum = 91;BA.debugLine="backgroundMusic.Looping = True";
_backgroundmusic.setLooping(__c.True);
 //BA.debugLineNum = 92;BA.debugLine="backgroundMusic.SetVolume(MUSIC_VOLUME, MUSIC_VO";
_backgroundmusic.SetVolume(_music_volume,_music_volume);
 //BA.debugLineNum = 94;BA.debugLine="lineClearSound.Load(File.DirAssets, LINE_CLEAR_F";
_lineclearsound.Load(__c.File.getDirAssets(),_line_clear_file);
 //BA.debugLineNum = 95;BA.debugLine="lineClearSound.Looping = False";
_lineclearsound.setLooping(__c.False);
 //BA.debugLineNum = 96;BA.debugLine="lineClearSound.SetVolume(SFX_VOLUME, SFX_VOLUME)";
_lineclearsound.SetVolume(_sfx_volume,_sfx_volume);
 //BA.debugLineNum = 98;BA.debugLine="IsAudioLoadedState = True";
_isaudioloadedstate = __c.True;
 //BA.debugLineNum = 99;BA.debugLine="Log(\"Audio loaded successfully\")";
__c.LogImpl("817498128","Audio loaded successfully",0);
 } 
       catch (Exception e15) {
			ba.setLastException(e15); //BA.debugLineNum = 101;BA.debugLine="Log(\"Error loading audio: \" & LastException.Mess";
__c.LogImpl("817498130","Error loading audio: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public String  _loadsettingsfromdatabase() throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Private Sub LoadSettingsFromDatabase";
 //BA.debugLineNum = 56;BA.debugLine="Try";
try { //BA.debugLineNum = 58;BA.debugLine="isMusicEnabled = settingsMgr.GetBGMEnabled";
_ismusicenabled = _settingsmgr._getbgmenabled /*boolean*/ ();
 //BA.debugLineNum = 59;BA.debugLine="areSoundEffectsEnabled = settingsMgr.GetSFXEnabl";
_aresoundeffectsenabled = _settingsmgr._getsfxenabled /*boolean*/ ();
 //BA.debugLineNum = 61;BA.debugLine="Log(\"Sound settings loaded - Music enabled: \" &";
__c.LogImpl("817367046","Sound settings loaded - Music enabled: "+BA.ObjectToString(_ismusicenabled)+", SFX enabled: "+BA.ObjectToString(_aresoundeffectsenabled),0);
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 63;BA.debugLine="Log(\"Error loading settings from database: \" & L";
__c.LogImpl("817367048","Error loading settings from database: "+__c.LastException(getActivityBA()).getMessage(),0);
 //BA.debugLineNum = 65;BA.debugLine="isMusicEnabled = True";
_ismusicenabled = __c.True;
 //BA.debugLineNum = 66;BA.debugLine="areSoundEffectsEnabled = True";
_aresoundeffectsenabled = __c.True;
 };
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public String  _pausemusic() throws Exception{
 //BA.debugLineNum = 141;BA.debugLine="Public Sub PauseMusic";
 //BA.debugLineNum = 142;BA.debugLine="Try";
try { //BA.debugLineNum = 143;BA.debugLine="If backgroundMusic.IsPlaying Then";
if (_backgroundmusic.IsPlaying()) { 
 //BA.debugLineNum = 144;BA.debugLine="backgroundMusic.Pause";
_backgroundmusic.Pause();
 };
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 147;BA.debugLine="Log(\"Error pausing music: \" & LastException.Mess";
__c.LogImpl("817694726","Error pausing music: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 149;BA.debugLine="End Sub";
return "";
}
public String  _playlineclearsound() throws Exception{
 //BA.debugLineNum = 203;BA.debugLine="Public Sub PlayLineClearSound";
 //BA.debugLineNum = 204;BA.debugLine="If Not(areSoundEffectsEnabled) Then Return";
if (__c.Not(_aresoundeffectsenabled)) { 
if (true) return "";};
 //BA.debugLineNum = 206;BA.debugLine="Try";
try { //BA.debugLineNum = 207;BA.debugLine="If lineClearSound.IsPlaying Then";
if (_lineclearsound.IsPlaying()) { 
 //BA.debugLineNum = 208;BA.debugLine="lineClearSound.Stop";
_lineclearsound.Stop();
 };
 //BA.debugLineNum = 211;BA.debugLine="lineClearSound.Position = 0";
_lineclearsound.setPosition((int) (0));
 //BA.debugLineNum = 212;BA.debugLine="lineClearSound.Play";
_lineclearsound.Play();
 } 
       catch (Exception e9) {
			ba.setLastException(e9); //BA.debugLineNum = 214;BA.debugLine="Log(\"Error playing line clear sound: \" & LastExc";
__c.LogImpl("818153483","Error playing line clear sound: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 216;BA.debugLine="End Sub";
return "";
}
public String  _release() throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Public Sub Release";
 //BA.debugLineNum = 222;BA.debugLine="Try";
try { //BA.debugLineNum = 224;BA.debugLine="StopMusic";
_stopmusic();
 //BA.debugLineNum = 227;BA.debugLine="If backgroundMusic.IsInitialized Then";
if (_backgroundmusic.IsInitialized()) { 
 //BA.debugLineNum = 228;BA.debugLine="backgroundMusic.Release";
_backgroundmusic.Release();
 };
 //BA.debugLineNum = 232;BA.debugLine="If lineClearSound.IsInitialized Then";
if (_lineclearsound.IsInitialized()) { 
 //BA.debugLineNum = 233;BA.debugLine="lineClearSound.Release";
_lineclearsound.Release();
 };
 //BA.debugLineNum = 236;BA.debugLine="Log(\"SoundManager released\")";
__c.LogImpl("818219023","SoundManager released",0);
 } 
       catch (Exception e11) {
			ba.setLastException(e11); //BA.debugLineNum = 238;BA.debugLine="Log(\"Error releasing SoundManager: \" & LastExcep";
__c.LogImpl("818219025","Error releasing SoundManager: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 240;BA.debugLine="End Sub";
return "";
}
public String  _resumemusic() throws Exception{
 //BA.debugLineNum = 151;BA.debugLine="Public Sub ResumeMusic";
 //BA.debugLineNum = 152;BA.debugLine="If Not(isMusicEnabled) Then Return";
if (__c.Not(_ismusicenabled)) { 
if (true) return "";};
 //BA.debugLineNum = 154;BA.debugLine="Try";
try { //BA.debugLineNum = 155;BA.debugLine="If Not(backgroundMusic.IsPlaying) Then";
if (__c.Not(_backgroundmusic.IsPlaying())) { 
 //BA.debugLineNum = 156;BA.debugLine="backgroundMusic.Play";
_backgroundmusic.Play();
 };
 } 
       catch (Exception e7) {
			ba.setLastException(e7); //BA.debugLineNum = 159;BA.debugLine="Log(\"Error resuming music: \" & LastException.Mes";
__c.LogImpl("817760264","Error resuming music: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return "";
}
public String  _savesettingstodatabase() throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Public Sub SaveSettingsToDatabase";
 //BA.debugLineNum = 71;BA.debugLine="Try";
try { //BA.debugLineNum = 73;BA.debugLine="settingsMgr.SaveSoundSettings(isMusicEnabled, ar";
_settingsmgr._savesoundsettings /*String*/ (_ismusicenabled,_aresoundeffectsenabled);
 //BA.debugLineNum = 74;BA.debugLine="Log(\"Sound settings saved - Music enabled: \" & i";
__c.LogImpl("817432580","Sound settings saved - Music enabled: "+BA.ObjectToString(_ismusicenabled)+", SFX enabled: "+BA.ObjectToString(_aresoundeffectsenabled),0);
 } 
       catch (Exception e5) {
			ba.setLastException(e5); //BA.debugLineNum = 76;BA.debugLine="Log(\"Error saving settings to database: \" & Last";
__c.LogImpl("817432582","Error saving settings to database: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
return "";
}
public String  _setbgmenabled(boolean _enabled) throws Exception{
 //BA.debugLineNum = 166;BA.debugLine="Public Sub SetBGMEnabled(enabled As Boolean)";
 //BA.debugLineNum = 167;BA.debugLine="isMusicEnabled = enabled";
_ismusicenabled = _enabled;
 //BA.debugLineNum = 168;BA.debugLine="SaveSettingsToDatabase";
_savesettingstodatabase();
 //BA.debugLineNum = 169;BA.debugLine="End Sub";
return "";
}
public String  _setsfxenabled(boolean _enabled) throws Exception{
 //BA.debugLineNum = 171;BA.debugLine="Public Sub SetSFXEnabled(enabled As Boolean)";
 //BA.debugLineNum = 172;BA.debugLine="areSoundEffectsEnabled = enabled";
_aresoundeffectsenabled = _enabled;
 //BA.debugLineNum = 173;BA.debugLine="SaveSettingsToDatabase";
_savesettingstodatabase();
 //BA.debugLineNum = 174;BA.debugLine="End Sub";
return "";
}
public String  _startmusic() throws Exception{
 //BA.debugLineNum = 108;BA.debugLine="Public Sub StartMusic";
 //BA.debugLineNum = 109;BA.debugLine="If Not(isMusicEnabled) Then Return";
if (__c.Not(_ismusicenabled)) { 
if (true) return "";};
 //BA.debugLineNum = 111;BA.debugLine="Try";
try { //BA.debugLineNum = 112;BA.debugLine="If Not(backgroundMusic.IsPlaying) Then";
if (__c.Not(_backgroundmusic.IsPlaying())) { 
 //BA.debugLineNum = 114;BA.debugLine="If Not(IsAudioLoadedState) Then";
if (__c.Not(_isaudioloadedstate)) { 
 //BA.debugLineNum = 115;BA.debugLine="Try";
try { //BA.debugLineNum = 116;BA.debugLine="backgroundMusic.Load(File.DirAssets, MUSIC_FI";
_backgroundmusic.Load(__c.File.getDirAssets(),_music_file);
 //BA.debugLineNum = 117;BA.debugLine="backgroundMusic.Looping = True";
_backgroundmusic.setLooping(__c.True);
 //BA.debugLineNum = 118;BA.debugLine="backgroundMusic.SetVolume(MUSIC_VOLUME, MUSIC";
_backgroundmusic.SetVolume(_music_volume,_music_volume);
 //BA.debugLineNum = 119;BA.debugLine="IsAudioLoadedState = True";
_isaudioloadedstate = __c.True;
 } 
       catch (Exception e11) {
			ba.setLastException(e11); };
 };
 //BA.debugLineNum = 124;BA.debugLine="backgroundMusic.Play";
_backgroundmusic.Play();
 };
 } 
       catch (Exception e16) {
			ba.setLastException(e16); //BA.debugLineNum = 127;BA.debugLine="Log(\"Error starting music: \" & LastException.Mes";
__c.LogImpl("817563667","Error starting music: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 129;BA.debugLine="End Sub";
return "";
}
public String  _stopmusic() throws Exception{
 //BA.debugLineNum = 131;BA.debugLine="Public Sub StopMusic";
 //BA.debugLineNum = 132;BA.debugLine="Try";
try { //BA.debugLineNum = 133;BA.debugLine="If backgroundMusic.IsPlaying Then";
if (_backgroundmusic.IsPlaying()) { 
 //BA.debugLineNum = 134;BA.debugLine="backgroundMusic.Stop";
_backgroundmusic.Stop();
 };
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 137;BA.debugLine="Log(\"Error stopping music: \" & LastException.Mes";
__c.LogImpl("817629190","Error stopping music: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 139;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
