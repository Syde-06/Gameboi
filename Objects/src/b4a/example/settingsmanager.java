package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class settingsmanager extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.settingsmanager");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.settingsmanager.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.sql.SQL _sql = null;
public String _db_name = "";
public String _table_name = "";
public String _key_bgm_enabled = "";
public String _key_sfx_enabled = "";
public boolean _default_bgm_enabled = false;
public boolean _default_sfx_enabled = false;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private sql As SQL";
_sql = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 7;BA.debugLine="Private DB_NAME As String = \"Database.db\"";
_db_name = "Database.db";
 //BA.debugLineNum = 8;BA.debugLine="Private TABLE_NAME As String = \"settings\"";
_table_name = "settings";
 //BA.debugLineNum = 11;BA.debugLine="Private Const KEY_BGM_ENABLED As String = \"bgm_en";
_key_bgm_enabled = "bgm_enabled";
 //BA.debugLineNum = 12;BA.debugLine="Private Const KEY_SFX_ENABLED As String = \"sfx_en";
_key_sfx_enabled = "sfx_enabled";
 //BA.debugLineNum = 15;BA.debugLine="Private Const DEFAULT_BGM_ENABLED As Boolean = Tr";
_default_bgm_enabled = __c.True;
 //BA.debugLineNum = 16;BA.debugLine="Private Const DEFAULT_SFX_ENABLED As Boolean = Tr";
_default_sfx_enabled = __c.True;
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public String  _clearallsettings() throws Exception{
String _query = "";
 //BA.debugLineNum = 183;BA.debugLine="Public Sub ClearAllSettings";
 //BA.debugLineNum = 184;BA.debugLine="Try";
try { //BA.debugLineNum = 185;BA.debugLine="Dim query As String = \"DELETE FROM \" & TABLE_NAM";
_query = "DELETE FROM "+_table_name;
 //BA.debugLineNum = 186;BA.debugLine="sql.ExecNonQuery(query)";
_sql.ExecNonQuery(_query);
 //BA.debugLineNum = 187;BA.debugLine="InitializeDefaultSettings";
_initializedefaultsettings();
 //BA.debugLineNum = 188;BA.debugLine="Log(\"All settings cleared and reset to defaults\"";
__c.LogImpl("815466501","All settings cleared and reset to defaults",0);
 } 
       catch (Exception e7) {
			ba.setLastException(e7); //BA.debugLineNum = 190;BA.debugLine="Log(\"Error clearing settings: \" & LastException.";
__c.LogImpl("815466503","Error clearing settings: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public String  _close() throws Exception{
 //BA.debugLineNum = 194;BA.debugLine="Public Sub Close";
 //BA.debugLineNum = 195;BA.debugLine="Try";
try { //BA.debugLineNum = 196;BA.debugLine="If sql.IsInitialized Then";
if (_sql.IsInitialized()) { 
 //BA.debugLineNum = 197;BA.debugLine="sql.Close";
_sql.Close();
 //BA.debugLineNum = 198;BA.debugLine="Log(\"Settings database connection closed\")";
__c.LogImpl("815532036","Settings database connection closed",0);
 };
 } 
       catch (Exception e7) {
			ba.setLastException(e7); //BA.debugLineNum = 201;BA.debugLine="Log(\"Error closing settings database: \" & LastEx";
__c.LogImpl("815532039","Error closing settings database: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 203;BA.debugLine="End Sub";
return "";
}
public String  _createtableifneeded() throws Exception{
String _query = "";
 //BA.debugLineNum = 33;BA.debugLine="Private Sub CreateTableIfNeeded";
 //BA.debugLineNum = 34;BA.debugLine="Try";
try { //BA.debugLineNum = 35;BA.debugLine="Dim query As String";
_query = "";
 //BA.debugLineNum = 36;BA.debugLine="query = \"CREATE TABLE IF NOT EXISTS \" & TABLE_NA";
_query = "CREATE TABLE IF NOT EXISTS "+_table_name+" ("+"setting_key TEXT PRIMARY KEY, "+"setting_value TEXT NOT NULL, "+"last_updated TEXT NOT NULL)";
 //BA.debugLineNum = 41;BA.debugLine="sql.ExecNonQuery(query)";
_sql.ExecNonQuery(_query);
 //BA.debugLineNum = 42;BA.debugLine="Log(\"Settings table created successfully\")";
__c.LogImpl("814680073","Settings table created successfully",0);
 } 
       catch (Exception e7) {
			ba.setLastException(e7); //BA.debugLineNum = 44;BA.debugLine="Log(\"Error creating settings table: \" & LastExce";
__c.LogImpl("814680075","Error creating settings table: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.Map  _getallsettings() throws Exception{
anywheresoftware.b4a.objects.collections.Map _settingsmap = null;
 //BA.debugLineNum = 166;BA.debugLine="Public Sub GetAllSettings As Map";
 //BA.debugLineNum = 167;BA.debugLine="Dim settingsMap As Map";
_settingsmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 168;BA.debugLine="settingsMap.Initialize";
_settingsmap.Initialize();
 //BA.debugLineNum = 170;BA.debugLine="Try";
try { //BA.debugLineNum = 171;BA.debugLine="settingsMap.Put(\"bgm_enabled\", GetBGMEnabled)";
_settingsmap.Put((Object)("bgm_enabled"),(Object)(_getbgmenabled()));
 //BA.debugLineNum = 172;BA.debugLine="settingsMap.Put(\"sfx_enabled\", GetSFXEnabled)";
_settingsmap.Put((Object)("sfx_enabled"),(Object)(_getsfxenabled()));
 } 
       catch (Exception e7) {
			ba.setLastException(e7); //BA.debugLineNum = 174;BA.debugLine="Log(\"Error getting all settings: \" & LastExcepti";
__c.LogImpl("815400968","Error getting all settings: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 177;BA.debugLine="Return settingsMap";
if (true) return _settingsmap;
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return null;
}
public boolean  _getbgmenabled() throws Exception{
 //BA.debugLineNum = 127;BA.debugLine="Public Sub GetBGMEnabled As Boolean";
 //BA.debugLineNum = 128;BA.debugLine="Return GetSetting(KEY_BGM_ENABLED, DEFAULT_BGM_EN";
if (true) return _getsetting(_key_bgm_enabled,_default_bgm_enabled);
 //BA.debugLineNum = 129;BA.debugLine="End Sub";
return false;
}
public String  _getdatabaseinfo() throws Exception{
String _info = "";
 //BA.debugLineNum = 205;BA.debugLine="Public Sub GetDatabaseInfo As String";
 //BA.debugLineNum = 206;BA.debugLine="Try";
try { //BA.debugLineNum = 207;BA.debugLine="Dim info As String = \"Settings Database Informat";
_info = "Settings Database Information:"+__c.CRLF;
 //BA.debugLineNum = 208;BA.debugLine="info = info & \"Database: \" & DB_NAME & CRLF";
_info = _info+"Database: "+_db_name+__c.CRLF;
 //BA.debugLineNum = 209;BA.debugLine="info = info & \"BGM Enabled: \" & GetBGMEnabled &";
_info = _info+"BGM Enabled: "+BA.ObjectToString(_getbgmenabled())+__c.CRLF;
 //BA.debugLineNum = 210;BA.debugLine="info = info & \"SFX Enabled: \" & GetSFXEnabled &";
_info = _info+"SFX Enabled: "+BA.ObjectToString(_getsfxenabled())+__c.CRLF;
 //BA.debugLineNum = 211;BA.debugLine="Return info";
if (true) return _info;
 } 
       catch (Exception e8) {
			ba.setLastException(e8); //BA.debugLineNum = 213;BA.debugLine="Return \"Error retrieving database info\"";
if (true) return "Error retrieving database info";
 };
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
return "";
}
public boolean  _getsetting(String _key,boolean _defaultvalue) throws Exception{
String _query = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor = null;
String _valuestr = "";
 //BA.debugLineNum = 102;BA.debugLine="Private Sub GetSetting(key As String, defaultValue";
 //BA.debugLineNum = 103;BA.debugLine="Try";
try { //BA.debugLineNum = 104;BA.debugLine="Dim query As String";
_query = "";
 //BA.debugLineNum = 105;BA.debugLine="query = \"SELECT setting_value FROM \" & TABLE_NAM";
_query = "SELECT setting_value FROM "+_table_name+" "+"WHERE setting_key = ?";
 //BA.debugLineNum = 108;BA.debugLine="Dim cursor As Cursor = sql.ExecQuery2(query, Arr";
_cursor = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_cursor = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery2(_query,new String[]{_key})));
 //BA.debugLineNum = 110;BA.debugLine="If cursor.RowCount > 0 Then";
if (_cursor.getRowCount()>0) { 
 //BA.debugLineNum = 111;BA.debugLine="cursor.Position = 0";
_cursor.setPosition((int) (0));
 //BA.debugLineNum = 112;BA.debugLine="Dim valueStr As String = cursor.GetString(\"sett";
_valuestr = _cursor.GetString("setting_value");
 //BA.debugLineNum = 113;BA.debugLine="cursor.Close";
_cursor.Close();
 //BA.debugLineNum = 116;BA.debugLine="Return valueStr = \"1\"";
if (true) return (_valuestr).equals("1");
 }else {
 //BA.debugLineNum = 118;BA.debugLine="cursor.Close";
_cursor.Close();
 //BA.debugLineNum = 119;BA.debugLine="Return defaultValue";
if (true) return _defaultvalue;
 };
 } 
       catch (Exception e15) {
			ba.setLastException(e15); //BA.debugLineNum = 122;BA.debugLine="Log(\"Error retrieving setting \" & key & \": \" & L";
__c.LogImpl("815073300","Error retrieving setting "+_key+": "+__c.LastException(getActivityBA()).getMessage(),0);
 //BA.debugLineNum = 123;BA.debugLine="Return defaultValue";
if (true) return _defaultvalue;
 };
 //BA.debugLineNum = 125;BA.debugLine="End Sub";
return false;
}
public boolean  _getsfxenabled() throws Exception{
 //BA.debugLineNum = 131;BA.debugLine="Public Sub GetSFXEnabled As Boolean";
 //BA.debugLineNum = 132;BA.debugLine="Return GetSetting(KEY_SFX_ENABLED, DEFAULT_SFX_EN";
if (true) return _getsetting(_key_sfx_enabled,_default_sfx_enabled);
 //BA.debugLineNum = 133;BA.debugLine="End Sub";
return false;
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 19;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 20;BA.debugLine="Try";
try { //BA.debugLineNum = 21;BA.debugLine="sql.Initialize(File.DirInternal, DB_NAME, True)";
_sql.Initialize(__c.File.getDirInternal(),_db_name,__c.True);
 //BA.debugLineNum = 22;BA.debugLine="CreateTableIfNeeded";
_createtableifneeded();
 //BA.debugLineNum = 23;BA.debugLine="InitializeDefaultSettings";
_initializedefaultsettings();
 //BA.debugLineNum = 24;BA.debugLine="Log(\"SettingsManager initialized successfully\")";
__c.LogImpl("814614533","SettingsManager initialized successfully",0);
 } 
       catch (Exception e7) {
			ba.setLastException(e7); //BA.debugLineNum = 26;BA.debugLine="Log(\"Error initializing SettingsManager: \" & Las";
__c.LogImpl("814614535","Error initializing SettingsManager: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public String  _initializedefaultsettings() throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Private Sub InitializeDefaultSettings";
 //BA.debugLineNum = 50;BA.debugLine="If SettingExists(KEY_BGM_ENABLED) = False Then";
if (_settingexists(_key_bgm_enabled)==__c.False) { 
 //BA.debugLineNum = 51;BA.debugLine="SaveSetting(KEY_BGM_ENABLED, DEFAULT_BGM_ENABLED";
_savesetting(_key_bgm_enabled,_default_bgm_enabled);
 };
 //BA.debugLineNum = 53;BA.debugLine="If SettingExists(KEY_SFX_ENABLED) = False Then";
if (_settingexists(_key_sfx_enabled)==__c.False) { 
 //BA.debugLineNum = 54;BA.debugLine="SaveSetting(KEY_SFX_ENABLED, DEFAULT_SFX_ENABLED";
_savesetting(_key_sfx_enabled,_default_sfx_enabled);
 };
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public String  _resettodefaults() throws Exception{
 //BA.debugLineNum = 156;BA.debugLine="Public Sub ResetToDefaults";
 //BA.debugLineNum = 157;BA.debugLine="Try";
try { //BA.debugLineNum = 158;BA.debugLine="SaveBGMEnabled(DEFAULT_BGM_ENABLED)";
_savebgmenabled(_default_bgm_enabled);
 //BA.debugLineNum = 159;BA.debugLine="SaveSFXEnabled(DEFAULT_SFX_ENABLED)";
_savesfxenabled(_default_sfx_enabled);
 //BA.debugLineNum = 160;BA.debugLine="Log(\"Settings reset to defaults\")";
__c.LogImpl("815335428","Settings reset to defaults",0);
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 162;BA.debugLine="Log(\"Error resetting settings: \" & LastException";
__c.LogImpl("815335430","Error resetting settings: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public String  _savebgmenabled(boolean _enabled) throws Exception{
 //BA.debugLineNum = 86;BA.debugLine="Public Sub SaveBGMEnabled(enabled As Boolean)";
 //BA.debugLineNum = 87;BA.debugLine="SaveSetting(KEY_BGM_ENABLED, enabled)";
_savesetting(_key_bgm_enabled,_enabled);
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public String  _savesetting(String _key,boolean _value) throws Exception{
String _timestamp = "";
String _query = "";
String _valuestr = "";
 //BA.debugLineNum = 61;BA.debugLine="Private Sub SaveSetting(key As String, value As Bo";
 //BA.debugLineNum = 62;BA.debugLine="Try";
try { //BA.debugLineNum = 63;BA.debugLine="Dim timestamp As String = DateTime.Date(DateTime";
_timestamp = __c.DateTime.Date(__c.DateTime.getNow())+" "+__c.DateTime.Time(__c.DateTime.getNow());
 //BA.debugLineNum = 64;BA.debugLine="Dim query As String";
_query = "";
 //BA.debugLineNum = 67;BA.debugLine="query = \"INSERT OR REPLACE INTO \" & TABLE_NAME &";
_query = "INSERT OR REPLACE INTO "+_table_name+" "+"(setting_key, setting_value, last_updated) "+"VALUES (?, ?, ?)";
 //BA.debugLineNum = 72;BA.debugLine="Dim valueStr As String";
_valuestr = "";
 //BA.debugLineNum = 73;BA.debugLine="If value Then";
if (_value) { 
 //BA.debugLineNum = 74;BA.debugLine="valueStr = \"1\"";
_valuestr = "1";
 }else {
 //BA.debugLineNum = 76;BA.debugLine="valueStr = \"0\"";
_valuestr = "0";
 };
 //BA.debugLineNum = 79;BA.debugLine="sql.ExecNonQuery2(query, Array As Object(key, va";
_sql.ExecNonQuery2(_query,anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_key),(Object)(_valuestr),(Object)(_timestamp)}));
 //BA.debugLineNum = 80;BA.debugLine="Log(\"Setting saved: \" & key & \" = \" & value)";
__c.LogImpl("814811155","Setting saved: "+_key+" = "+BA.ObjectToString(_value),0);
 } 
       catch (Exception e14) {
			ba.setLastException(e14); //BA.debugLineNum = 82;BA.debugLine="Log(\"Error saving setting \" & key & \": \" & LastE";
__c.LogImpl("814811157","Error saving setting "+_key+": "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}
public String  _savesfxenabled(boolean _enabled) throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Public Sub SaveSFXEnabled(enabled As Boolean)";
 //BA.debugLineNum = 91;BA.debugLine="SaveSetting(KEY_SFX_ENABLED, enabled)";
_savesetting(_key_sfx_enabled,_enabled);
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return "";
}
public String  _savesoundsettings(boolean _bgmenabled,boolean _sfxenabled) throws Exception{
 //BA.debugLineNum = 94;BA.debugLine="Public Sub SaveSoundSettings(bgmEnabled As Boolean";
 //BA.debugLineNum = 95;BA.debugLine="SaveBGMEnabled(bgmEnabled)";
_savebgmenabled(_bgmenabled);
 //BA.debugLineNum = 96;BA.debugLine="SaveSFXEnabled(sfxEnabled)";
_savesfxenabled(_sfxenabled);
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public boolean  _settingexists(String _key) throws Exception{
String _query = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor = null;
int _count = 0;
 //BA.debugLineNum = 138;BA.debugLine="Private Sub SettingExists(key As String) As Boolea";
 //BA.debugLineNum = 139;BA.debugLine="Try";
try { //BA.debugLineNum = 140;BA.debugLine="Dim query As String";
_query = "";
 //BA.debugLineNum = 141;BA.debugLine="query = \"SELECT COUNT(*) as count FROM \" & TABLE";
_query = "SELECT COUNT(*) as count FROM "+_table_name+" "+"WHERE setting_key = ?";
 //BA.debugLineNum = 144;BA.debugLine="Dim cursor As Cursor = sql.ExecQuery2(query, Arr";
_cursor = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_cursor = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery2(_query,new String[]{_key})));
 //BA.debugLineNum = 145;BA.debugLine="cursor.Position = 0";
_cursor.setPosition((int) (0));
 //BA.debugLineNum = 146;BA.debugLine="Dim count As Int = cursor.GetInt(\"count\")";
_count = _cursor.GetInt("count");
 //BA.debugLineNum = 147;BA.debugLine="cursor.Close";
_cursor.Close();
 //BA.debugLineNum = 149;BA.debugLine="Return count > 0";
if (true) return _count>0;
 } 
       catch (Exception e10) {
			ba.setLastException(e10); //BA.debugLineNum = 151;BA.debugLine="Log(\"Error checking if setting exists: \" & LastE";
__c.LogImpl("815269901","Error checking if setting exists: "+__c.LastException(getActivityBA()).getMessage(),0);
 //BA.debugLineNum = 152;BA.debugLine="Return False";
if (true) return __c.False;
 };
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return false;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
