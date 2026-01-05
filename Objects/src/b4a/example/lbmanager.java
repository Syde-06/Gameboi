package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class lbmanager extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.lbmanager");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.lbmanager.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.sql.SQL _sql = null;
public String _db_name = "";
public String _currenttablename = "";
public int _max_name_length = 0;
public String _table_tetris = "";
public String _table_snake = "";
public String _table_space = "";
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public boolean  _addscore(String _playername,int _score) throws Exception{
String _timestamp = "";
String _query = "";
 //BA.debugLineNum = 66;BA.debugLine="Public Sub AddScore(playerName As String, score As";
 //BA.debugLineNum = 67;BA.debugLine="Try";
try { //BA.debugLineNum = 69;BA.debugLine="If playerName.Trim.Length = 0 Then";
if (_playername.trim().length()==0) { 
 //BA.debugLineNum = 70;BA.debugLine="playerName = \"Anonymous\"";
_playername = "Anonymous";
 };
 //BA.debugLineNum = 73;BA.debugLine="If playerName.Length > MAX_NAME_LENGTH Then";
if (_playername.length()>_max_name_length) { 
 //BA.debugLineNum = 74;BA.debugLine="playerName = playerName.SubString2(0, MAX_NAME_";
_playername = _playername.substring((int) (0),_max_name_length);
 };
 //BA.debugLineNum = 78;BA.debugLine="Dim timestamp As String = DateTime.Date(DateTime";
_timestamp = __c.DateTime.Date(__c.DateTime.getNow())+" "+__c.DateTime.Time(__c.DateTime.getNow());
 //BA.debugLineNum = 81;BA.debugLine="Dim query As String = _             \"INSERT INTO";
_query = "INSERT INTO "+_currenttablename+" (player_name, score, date_played) "+"VALUES (?, ?, ?)";
 //BA.debugLineNum = 85;BA.debugLine="sql.ExecNonQuery2(query, Array As Object(playerN";
_sql.ExecNonQuery2(_query,anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_playername),(Object)(_score),(Object)(_timestamp)}));
 //BA.debugLineNum = 87;BA.debugLine="Log(\"Score added successfully to \" & currentTabl";
__c.LogImpl("813238293","Score added successfully to "+_currenttablename+": "+_playername+" - "+BA.NumberToString(_score),0);
 //BA.debugLineNum = 88;BA.debugLine="Return True";
if (true) return __c.True;
 } 
       catch (Exception e14) {
			ba.setLastException(e14); //BA.debugLineNum = 90;BA.debugLine="Log(\"Error adding score: \" & LastException.Messa";
__c.LogImpl("813238296","Error adding score: "+__c.LastException(getActivityBA()).getMessage(),0);
 //BA.debugLineNum = 91;BA.debugLine="Return False";
if (true) return __c.False;
 };
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return false;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private sql As SQL";
_sql = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 8;BA.debugLine="Private DB_NAME As String = \"Database.db\"";
_db_name = "Database.db";
 //BA.debugLineNum = 9;BA.debugLine="Public currentTableName As String = \"leaderboard_";
_currenttablename = "leaderboard_tetris";
 //BA.debugLineNum = 10;BA.debugLine="Private MAX_NAME_LENGTH As Int = 20";
_max_name_length = (int) (20);
 //BA.debugLineNum = 13;BA.debugLine="Public Const TABLE_TETRIS As String = \"leaderboar";
_table_tetris = "leaderboard_tetris";
 //BA.debugLineNum = 14;BA.debugLine="Public Const TABLE_SNAKE As String = \"leaderboard";
_table_snake = "leaderboard_snake";
 //BA.debugLineNum = 15;BA.debugLine="Public Const TABLE_SPACE As String = \"leaderboard";
_table_space = "leaderboard_space";
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public String  _clearallscores() throws Exception{
String _query = "";
 //BA.debugLineNum = 238;BA.debugLine="Public Sub ClearAllScores";
 //BA.debugLineNum = 239;BA.debugLine="Try";
try { //BA.debugLineNum = 240;BA.debugLine="Dim query As String = \"DELETE FROM \" & currentTa";
_query = "DELETE FROM "+_currenttablename;
 //BA.debugLineNum = 241;BA.debugLine="sql.ExecNonQuery(query)";
_sql.ExecNonQuery(_query);
 //BA.debugLineNum = 242;BA.debugLine="Log(\"All scores cleared from leaderboard: \" & cu";
__c.LogImpl("813631492","All scores cleared from leaderboard: "+_currenttablename,0);
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 244;BA.debugLine="Log(\"Error clearing scores: \" & LastException.Me";
__c.LogImpl("813631494","Error clearing scores: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 246;BA.debugLine="End Sub";
return "";
}
public String  _close() throws Exception{
 //BA.debugLineNum = 261;BA.debugLine="Public Sub Close";
 //BA.debugLineNum = 262;BA.debugLine="Try";
try { //BA.debugLineNum = 263;BA.debugLine="If sql.IsInitialized Then";
if (_sql.IsInitialized()) { 
 //BA.debugLineNum = 264;BA.debugLine="sql.Close";
_sql.Close();
 //BA.debugLineNum = 265;BA.debugLine="Log(\"Database connection closed\")";
__c.LogImpl("813762564","Database connection closed",0);
 };
 } 
       catch (Exception e7) {
			ba.setLastException(e7); //BA.debugLineNum = 268;BA.debugLine="Log(\"Error closing database: \" & LastException.M";
__c.LogImpl("813762567","Error closing database: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return "";
}
public String  _createalltables() throws Exception{
 //BA.debugLineNum = 40;BA.debugLine="Private Sub CreateAllTables";
 //BA.debugLineNum = 42;BA.debugLine="CreateTableIfNeeded(TABLE_TETRIS)";
_createtableifneeded(_table_tetris);
 //BA.debugLineNum = 43;BA.debugLine="CreateTableIfNeeded(TABLE_SNAKE)";
_createtableifneeded(_table_snake);
 //BA.debugLineNum = 44;BA.debugLine="CreateTableIfNeeded(TABLE_SPACE)";
_createtableifneeded(_table_space);
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public String  _createtableifneeded(String _tablename) throws Exception{
String _query = "";
 //BA.debugLineNum = 47;BA.debugLine="Private Sub CreateTableIfNeeded(tableName As Strin";
 //BA.debugLineNum = 48;BA.debugLine="Try";
try { //BA.debugLineNum = 49;BA.debugLine="Dim query As String = _             \"CREATE TABL";
_query = "CREATE TABLE IF NOT EXISTS "+_tablename+" ("+"id INTEGER PRIMARY KEY AUTOINCREMENT, "+"player_name TEXT NOT NULL, "+"score INTEGER NOT NULL, "+"date_played TEXT Not Null)";
 //BA.debugLineNum = 56;BA.debugLine="sql.ExecNonQuery(query)";
_sql.ExecNonQuery(_query);
 //BA.debugLineNum = 57;BA.debugLine="Log(\"Leaderboard table initialized successfully:";
__c.LogImpl("813172746","Leaderboard table initialized successfully: "+_tablename,0);
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 59;BA.debugLine="Log(\"Error creating leaderboard table: \" & LastE";
__c.LogImpl("813172748","Error creating leaderboard table: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
public String  _getcurrentgame() throws Exception{
 //BA.debugLineNum = 248;BA.debugLine="Public Sub GetCurrentGame As String";
 //BA.debugLineNum = 249;BA.debugLine="Select currentTableName";
switch (BA.switchObjectToInt(_currenttablename,_table_tetris,_table_snake,_table_space)) {
case 0: {
 //BA.debugLineNum = 251;BA.debugLine="Return \"Tetris\"";
if (true) return "Tetris";
 break; }
case 1: {
 //BA.debugLineNum = 253;BA.debugLine="Return \"Snake\"";
if (true) return "Snake";
 break; }
case 2: {
 //BA.debugLineNum = 255;BA.debugLine="Return \"Space Invaders\"";
if (true) return "Space Invaders";
 break; }
default: {
 //BA.debugLineNum = 257;BA.debugLine="Return \"Unknown\"";
if (true) return "Unknown";
 break; }
}
;
 //BA.debugLineNum = 259;BA.debugLine="End Sub";
return "";
}
public int  _getplayerbestscore(String _playername) throws Exception{
String _query = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor = null;
int _bestscore = 0;
 //BA.debugLineNum = 207;BA.debugLine="Public Sub GetPlayerBestScore(playerName As String";
 //BA.debugLineNum = 208;BA.debugLine="Try";
try { //BA.debugLineNum = 209;BA.debugLine="Dim query As String = _             \"SELECT MAX(";
_query = "SELECT MAX(score) as best_score "+"FROM "+_currenttablename+" "+"WHERE player_name = ?";
 //BA.debugLineNum = 214;BA.debugLine="Dim cursor As Cursor = sql.ExecQuery2(query, Arr";
_cursor = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_cursor = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery2(_query,new String[]{_playername})));
 //BA.debugLineNum = 216;BA.debugLine="Dim bestScore As Int = 0";
_bestscore = (int) (0);
 //BA.debugLineNum = 217;BA.debugLine="If cursor.RowCount > 0 Then";
if (_cursor.getRowCount()>0) { 
 //BA.debugLineNum = 218;BA.debugLine="cursor.Position = 0";
_cursor.setPosition((int) (0));
 //BA.debugLineNum = 219;BA.debugLine="Try";
try { //BA.debugLineNum = 220;BA.debugLine="bestScore = cursor.GetInt(\"best_score\")";
_bestscore = _cursor.GetInt("best_score");
 } 
       catch (Exception e10) {
			ba.setLastException(e10); //BA.debugLineNum = 223;BA.debugLine="bestScore = 0";
_bestscore = (int) (0);
 };
 };
 //BA.debugLineNum = 227;BA.debugLine="cursor.Close";
_cursor.Close();
 //BA.debugLineNum = 228;BA.debugLine="Return bestScore";
if (true) return _bestscore;
 } 
       catch (Exception e16) {
			ba.setLastException(e16); //BA.debugLineNum = 230;BA.debugLine="Log(\"Error retrieving player's best score: \" & L";
__c.LogImpl("813565975","Error retrieving player's best score: "+__c.LastException(getActivityBA()).getMessage(),0);
 //BA.debugLineNum = 231;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return 0;
}
public int  _getplayerrank(int _score) throws Exception{
String _query = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor = null;
int _rank = 0;
 //BA.debugLineNum = 169;BA.debugLine="Public Sub GetPlayerRank(score As Int) As Int";
 //BA.debugLineNum = 170;BA.debugLine="Try";
try { //BA.debugLineNum = 171;BA.debugLine="Dim query As String = _             \"SELECT COUN";
_query = "SELECT COUNT(*) + 1 as rank "+"FROM "+_currenttablename+" "+"WHERE score > ?";
 //BA.debugLineNum = 176;BA.debugLine="Dim cursor As Cursor = sql.ExecQuery2(query, Arr";
_cursor = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_cursor = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery2(_query,new String[]{BA.NumberToString(_score)})));
 //BA.debugLineNum = 177;BA.debugLine="cursor.Position = 0";
_cursor.setPosition((int) (0));
 //BA.debugLineNum = 178;BA.debugLine="Dim rank As Int = cursor.GetInt(\"rank\")";
_rank = _cursor.GetInt("rank");
 //BA.debugLineNum = 179;BA.debugLine="cursor.Close";
_cursor.Close();
 //BA.debugLineNum = 181;BA.debugLine="Return rank";
if (true) return _rank;
 } 
       catch (Exception e9) {
			ba.setLastException(e9); //BA.debugLineNum = 183;BA.debugLine="Log(\"Error calculating player rank: \" & LastExce";
__c.LogImpl("813434894","Error calculating player rank: "+__c.LastException(getActivityBA()).getMessage(),0);
 //BA.debugLineNum = 184;BA.debugLine="Return -1";
if (true) return (int) (-1);
 };
 //BA.debugLineNum = 186;BA.debugLine="End Sub";
return 0;
}
public anywheresoftware.b4a.objects.collections.List  _gettopscores(int _limit) throws Exception{
anywheresoftware.b4a.objects.collections.List _topscores = null;
String _query = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _scoredata = null;
 //BA.debugLineNum = 99;BA.debugLine="Public Sub GetTopScores(limit As Int) As List";
 //BA.debugLineNum = 100;BA.debugLine="Dim topScores As List";
_topscores = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 101;BA.debugLine="topScores.Initialize";
_topscores.Initialize();
 //BA.debugLineNum = 103;BA.debugLine="Try";
try { //BA.debugLineNum = 104;BA.debugLine="Dim query As String = _             \"SELECT play";
_query = "SELECT player_name, score, date_played "+"FROM "+_currenttablename+" "+"ORDER BY score DESC "+"LIMIT ?";
 //BA.debugLineNum = 110;BA.debugLine="Dim cursor As Cursor = sql.ExecQuery2(query, Arr";
_cursor = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_cursor = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery2(_query,new String[]{BA.NumberToString(_limit)})));
 //BA.debugLineNum = 112;BA.debugLine="For i = 0 To cursor.RowCount - 1";
{
final int step6 = 1;
final int limit6 = (int) (_cursor.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
 //BA.debugLineNum = 113;BA.debugLine="cursor.Position = i";
_cursor.setPosition(_i);
 //BA.debugLineNum = 115;BA.debugLine="Dim scoreData As Map";
_scoredata = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 116;BA.debugLine="scoreData.Initialize";
_scoredata.Initialize();
 //BA.debugLineNum = 117;BA.debugLine="scoreData.Put(\"player_name\", cursor.GetString(\"";
_scoredata.Put((Object)("player_name"),(Object)(_cursor.GetString("player_name")));
 //BA.debugLineNum = 118;BA.debugLine="scoreData.Put(\"score\", cursor.GetInt(\"score\"))";
_scoredata.Put((Object)("score"),(Object)(_cursor.GetInt("score")));
 //BA.debugLineNum = 119;BA.debugLine="scoreData.Put(\"date_played\", cursor.GetString(\"";
_scoredata.Put((Object)("date_played"),(Object)(_cursor.GetString("date_played")));
 //BA.debugLineNum = 121;BA.debugLine="topScores.Add(scoreData)";
_topscores.Add((Object)(_scoredata.getObject()));
 }
};
 //BA.debugLineNum = 124;BA.debugLine="cursor.Close";
_cursor.Close();
 } 
       catch (Exception e17) {
			ba.setLastException(e17); //BA.debugLineNum = 126;BA.debugLine="Log(\"Error retrieving top scores: \" & LastExcept";
__c.LogImpl("813303835","Error retrieving top scores: "+__c.LastException(getActivityBA()).getMessage(),0);
 };
 //BA.debugLineNum = 129;BA.debugLine="Return topScores";
if (true) return _topscores;
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return null;
}
public int  _gettotalscorescount() throws Exception{
String _query = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor = null;
int _count = 0;
 //BA.debugLineNum = 191;BA.debugLine="Public Sub GetTotalScoresCount As Int";
 //BA.debugLineNum = 192;BA.debugLine="Try";
try { //BA.debugLineNum = 193;BA.debugLine="Dim query As String = \"SELECT COUNT(*) as count";
_query = "SELECT COUNT(*) as count FROM "+_currenttablename;
 //BA.debugLineNum = 195;BA.debugLine="Dim cursor As Cursor = sql.ExecQuery(query)";
_cursor = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_cursor = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery(_query)));
 //BA.debugLineNum = 196;BA.debugLine="cursor.Position = 0";
_cursor.setPosition((int) (0));
 //BA.debugLineNum = 197;BA.debugLine="Dim count As Int = cursor.GetInt(\"count\")";
_count = _cursor.GetInt("count");
 //BA.debugLineNum = 198;BA.debugLine="cursor.Close";
_cursor.Close();
 //BA.debugLineNum = 200;BA.debugLine="Return count";
if (true) return _count;
 } 
       catch (Exception e9) {
			ba.setLastException(e9); //BA.debugLineNum = 202;BA.debugLine="Log(\"Error getting total score count: \" & LastEx";
__c.LogImpl("813500427","Error getting total score count: "+__c.LastException(getActivityBA()).getMessage(),0);
 //BA.debugLineNum = 203;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 205;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 18;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 19;BA.debugLine="Initialize2(\"\")";
_initialize2("");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public String  _initialize2(String _gamename) throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Public Sub Initialize2(gameName As String)";
 //BA.debugLineNum = 23;BA.debugLine="sql.Initialize(File.DirInternal, DB_NAME, True)";
_sql.Initialize(__c.File.getDirInternal(),_db_name,__c.True);
 //BA.debugLineNum = 24;BA.debugLine="Select gameName.ToLowerCase";
switch (BA.switchObjectToInt(_gamename.toLowerCase(),"tetris","snake","space","spaceinvaders","invaders")) {
case 0: {
 //BA.debugLineNum = 26;BA.debugLine="currentTableName = TABLE_TETRIS";
_currenttablename = _table_tetris;
 break; }
case 1: {
 //BA.debugLineNum = 28;BA.debugLine="currentTableName = TABLE_SNAKE";
_currenttablename = _table_snake;
 break; }
case 2: 
case 3: 
case 4: {
 //BA.debugLineNum = 30;BA.debugLine="currentTableName = TABLE_SPACE";
_currenttablename = _table_space;
 break; }
default: {
 //BA.debugLineNum = 32;BA.debugLine="currentTableName = TABLE_TETRIS";
_currenttablename = _table_tetris;
 break; }
}
;
 //BA.debugLineNum = 34;BA.debugLine="CreateAllTables";
_createalltables();
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return "";
}
public boolean  _ishighscore(int _score,int _topn) throws Exception{
int _totalscores = 0;
String _query = "";
anywheresoftware.b4a.sql.SQL.CursorWrapper _cursor = null;
int _lowesttopscore = 0;
 //BA.debugLineNum = 135;BA.debugLine="Public Sub IsHighScore(score As Int, topN As Int)";
 //BA.debugLineNum = 136;BA.debugLine="Try";
try { //BA.debugLineNum = 138;BA.debugLine="Dim totalScores As Int = GetTotalScoresCount";
_totalscores = _gettotalscorescount();
 //BA.debugLineNum = 141;BA.debugLine="If totalScores < topN Then";
if (_totalscores<_topn) { 
 //BA.debugLineNum = 142;BA.debugLine="Return True";
if (true) return __c.True;
 };
 //BA.debugLineNum = 146;BA.debugLine="Dim query As String = _             \"SELECT scor";
_query = "SELECT score FROM "+_currenttablename+" "+"ORDER BY score DESC LIMIT ?";
 //BA.debugLineNum = 150;BA.debugLine="Dim cursor As Cursor = sql.ExecQuery2(query, Arr";
_cursor = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_cursor = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery2(_query,new String[]{BA.NumberToString(_topn)})));
 //BA.debugLineNum = 152;BA.debugLine="Dim lowestTopScore As Int = 0";
_lowesttopscore = (int) (0);
 //BA.debugLineNum = 153;BA.debugLine="If cursor.RowCount > 0 Then";
if (_cursor.getRowCount()>0) { 
 //BA.debugLineNum = 154;BA.debugLine="cursor.Position = cursor.RowCount - 1";
_cursor.setPosition((int) (_cursor.getRowCount()-1));
 //BA.debugLineNum = 155;BA.debugLine="lowestTopScore = cursor.GetInt(\"score\")";
_lowesttopscore = _cursor.GetInt("score");
 };
 //BA.debugLineNum = 157;BA.debugLine="cursor.Close";
_cursor.Close();
 //BA.debugLineNum = 159;BA.debugLine="Return score > lowestTopScore";
if (true) return _score>_lowesttopscore;
 } 
       catch (Exception e16) {
			ba.setLastException(e16); //BA.debugLineNum = 161;BA.debugLine="Log(\"Error checking high score status: \" & LastE";
__c.LogImpl("813369370","Error checking high score status: "+__c.LastException(getActivityBA()).getMessage(),0);
 //BA.debugLineNum = 162;BA.debugLine="Return False";
if (true) return __c.False;
 };
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return false;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
