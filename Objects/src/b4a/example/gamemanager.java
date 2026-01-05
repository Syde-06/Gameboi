package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class gamemanager extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.gamemanager");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.gamemanager.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public b4a.example.grid _grid = null;
public b4a.example.tetromino _activepiece = null;
public b4a.example.tetromino _nextpiece = null;
public b4a.example.collisionmanager _collisionmanager = null;
public b4a.example.piecefactory _piecefactory = null;
public b4a.example.soundmanager _soundmanager = null;
public boolean _isactivestate = false;
public boolean _ispausedstate = false;
public int _currentscore = 0;
public int _currentlevel = 0;
public int _totallinescleared = 0;
public int _combocount = 0;
public int _combotimer = 0;
public float _combomultiplier = 0f;
public int _combo_timeout = 0;
public float _piecey = 0f;
public int _piecetargety = 0;
public float _drop_smoothness = 0f;
public anywheresoftware.b4a.objects.collections.List _clearinglines = null;
public int _clearanimtimer = 0;
public int _clear_anim_duration = 0;
public int _tickcounter = 0;
public int _ticksperdrop = 0;
public boolean _issoftdropactive = false;
public int _base_ticks_per_drop = 0;
public int _points_single = 0;
public int _points_double = 0;
public int _points_triple = 0;
public int _points_tetris = 0;
public int _points_soft_drop = 0;
public int _points_hard_drop_row = 0;
public int _combo_bonus_per_line = 0;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _awardpoints(int _linecount) throws Exception{
int _basepoints = 0;
int _points = 0;
int _combobonus = 0;
 //BA.debugLineNum = 291;BA.debugLine="Private Sub AwardPoints(lineCount As Int)";
 //BA.debugLineNum = 292;BA.debugLine="Dim basePoints As Int";
_basepoints = 0;
 //BA.debugLineNum = 294;BA.debugLine="Select lineCount";
switch (_linecount) {
case 1: {
 //BA.debugLineNum = 295;BA.debugLine="Case 1: basePoints = POINTS_SINGLE";
_basepoints = _points_single;
 break; }
case 2: {
 //BA.debugLineNum = 296;BA.debugLine="Case 2: basePoints = POINTS_DOUBLE";
_basepoints = _points_double;
 break; }
case 3: {
 //BA.debugLineNum = 297;BA.debugLine="Case 3: basePoints = POINTS_TRIPLE";
_basepoints = _points_triple;
 break; }
case 4: {
 //BA.debugLineNum = 298;BA.debugLine="Case 4: basePoints = POINTS_TETRIS";
_basepoints = _points_tetris;
 break; }
default: {
 //BA.debugLineNum = 299;BA.debugLine="Case Else: basePoints = 0";
_basepoints = (int) (0);
 break; }
}
;
 //BA.debugLineNum = 303;BA.debugLine="Dim points As Int = basePoints * currentLevel";
_points = (int) (_basepoints*_currentlevel);
 //BA.debugLineNum = 306;BA.debugLine="points = points * comboMultiplier";
_points = (int) (_points*_combomultiplier);
 //BA.debugLineNum = 309;BA.debugLine="If comboCount > 1 Then";
if (_combocount>1) { 
 //BA.debugLineNum = 310;BA.debugLine="Dim comboBonus As Int = (comboCount - 1) * COMBO";
_combobonus = (int) ((_combocount-1)*_combo_bonus_per_line*_linecount);
 //BA.debugLineNum = 311;BA.debugLine="points = points + comboBonus";
_points = (int) (_points+_combobonus);
 };
 //BA.debugLineNum = 314;BA.debugLine="currentScore = currentScore + points";
_currentscore = (int) (_currentscore+_points);
 //BA.debugLineNum = 315;BA.debugLine="totalLinesCleared = totalLinesCleared + lineCount";
_totallinescleared = (int) (_totallinescleared+_linecount);
 //BA.debugLineNum = 316;BA.debugLine="End Sub";
return "";
}
public int  _calculatecurrentdropspeed() throws Exception{
 //BA.debugLineNum = 141;BA.debugLine="Private Sub CalculateCurrentDropSpeed As Int";
 //BA.debugLineNum = 142;BA.debugLine="If isSoftDropActive Then";
if (_issoftdropactive) { 
 //BA.debugLineNum = 143;BA.debugLine="Return Max(2, ticksPerDrop / 4)";
if (true) return (int) (__c.Max(2,_ticksperdrop/(double)4));
 };
 //BA.debugLineNum = 145;BA.debugLine="Return ticksPerDrop";
if (true) return _ticksperdrop;
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return 0;
}
public int  _calculatedropposition() throws Exception{
int _testy = 0;
 //BA.debugLineNum = 252;BA.debugLine="Public Sub CalculateDropPosition As Int";
 //BA.debugLineNum = 253;BA.debugLine="Dim testY As Int = activePiece.GetY";
_testy = _activepiece._gety /*int*/ ();
 //BA.debugLineNum = 255;BA.debugLine="Do While collisionManager.IsValidPosition(activeP";
while (_collisionmanager._isvalidposition /*boolean*/ (_activepiece._getx /*int*/ (),(int) (_testy+1),_activepiece._getshape /*int[][]*/ ())) {
 //BA.debugLineNum = 256;BA.debugLine="testY = testY + 1";
_testy = (int) (_testy+1);
 }
;
 //BA.debugLineNum = 259;BA.debugLine="Return testY";
if (true) return _testy;
 //BA.debugLineNum = 260;BA.debugLine="End Sub";
return 0;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private grid As Grid";
_grid = new b4a.example.grid();
 //BA.debugLineNum = 7;BA.debugLine="Private activePiece As Tetromino";
_activepiece = new b4a.example.tetromino();
 //BA.debugLineNum = 8;BA.debugLine="Private nextPiece As Tetromino";
_nextpiece = new b4a.example.tetromino();
 //BA.debugLineNum = 9;BA.debugLine="Private collisionManager As CollisionManager";
_collisionmanager = new b4a.example.collisionmanager();
 //BA.debugLineNum = 10;BA.debugLine="Private pieceFactory As PieceFactory";
_piecefactory = new b4a.example.piecefactory();
 //BA.debugLineNum = 11;BA.debugLine="Private soundManager As SoundManager";
_soundmanager = new b4a.example.soundmanager();
 //BA.debugLineNum = 13;BA.debugLine="Public IsActiveState As Boolean";
_isactivestate = false;
 //BA.debugLineNum = 14;BA.debugLine="Public IsPausedState As Boolean";
_ispausedstate = false;
 //BA.debugLineNum = 15;BA.debugLine="Private currentScore As Int";
_currentscore = 0;
 //BA.debugLineNum = 16;BA.debugLine="Private currentLevel As Int";
_currentlevel = 0;
 //BA.debugLineNum = 17;BA.debugLine="Private totalLinesCleared As Int";
_totallinescleared = 0;
 //BA.debugLineNum = 20;BA.debugLine="Private comboCount As Int";
_combocount = 0;
 //BA.debugLineNum = 21;BA.debugLine="Private comboTimer As Int";
_combotimer = 0;
 //BA.debugLineNum = 22;BA.debugLine="Private comboMultiplier As Float = 1.0";
_combomultiplier = (float) (1.0);
 //BA.debugLineNum = 23;BA.debugLine="Private Const COMBO_TIMEOUT As Int = 120  ' Frame";
_combo_timeout = (int) (120);
 //BA.debugLineNum = 26;BA.debugLine="Private pieceY As Float  ' Interpolated Y positio";
_piecey = 0f;
 //BA.debugLineNum = 27;BA.debugLine="Private pieceTargetY As Int  ' Target Y position";
_piecetargety = 0;
 //BA.debugLineNum = 28;BA.debugLine="Private Const DROP_SMOOTHNESS As Float = 0.3";
_drop_smoothness = (float) (0.3);
 //BA.debugLineNum = 31;BA.debugLine="Private clearingLines As List";
_clearinglines = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 32;BA.debugLine="Private clearAnimTimer As Int";
_clearanimtimer = 0;
 //BA.debugLineNum = 33;BA.debugLine="Private Const CLEAR_ANIM_DURATION As Int = 20";
_clear_anim_duration = (int) (20);
 //BA.debugLineNum = 36;BA.debugLine="Private tickCounter As Int";
_tickcounter = 0;
 //BA.debugLineNum = 37;BA.debugLine="Private ticksPerDrop As Int";
_ticksperdrop = 0;
 //BA.debugLineNum = 38;BA.debugLine="Private isSoftDropActive As Boolean";
_issoftdropactive = false;
 //BA.debugLineNum = 39;BA.debugLine="Private Const BASE_TICKS_PER_DROP As Int = 30";
_base_ticks_per_drop = (int) (30);
 //BA.debugLineNum = 42;BA.debugLine="Private Const POINTS_SINGLE As Int = 100";
_points_single = (int) (100);
 //BA.debugLineNum = 43;BA.debugLine="Private Const POINTS_DOUBLE As Int = 300";
_points_double = (int) (300);
 //BA.debugLineNum = 44;BA.debugLine="Private Const POINTS_TRIPLE As Int = 500";
_points_triple = (int) (500);
 //BA.debugLineNum = 45;BA.debugLine="Private Const POINTS_TETRIS As Int = 800";
_points_tetris = (int) (800);
 //BA.debugLineNum = 46;BA.debugLine="Private Const POINTS_SOFT_DROP As Int = 1";
_points_soft_drop = (int) (1);
 //BA.debugLineNum = 47;BA.debugLine="Private Const POINTS_HARD_DROP_ROW As Int = 2";
_points_hard_drop_row = (int) (2);
 //BA.debugLineNum = 48;BA.debugLine="Private Const COMBO_BONUS_PER_LINE As Int = 50";
_combo_bonus_per_line = (int) (50);
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public String  _completeclearanimation() throws Exception{
int _linecount = 0;
 //BA.debugLineNum = 171;BA.debugLine="Private Sub CompleteClearAnimation";
 //BA.debugLineNum = 173;BA.debugLine="grid.ClearLines(clearingLines)";
_grid._clearlines /*String*/ (_clearinglines);
 //BA.debugLineNum = 176;BA.debugLine="Dim lineCount As Int = clearingLines.Size";
_linecount = _clearinglines.getSize();
 //BA.debugLineNum = 177;BA.debugLine="AwardPoints(lineCount)";
_awardpoints(_linecount);
 //BA.debugLineNum = 178;BA.debugLine="UpdateLevel";
_updatelevel();
 //BA.debugLineNum = 181;BA.debugLine="comboCount = comboCount + 1";
_combocount = (int) (_combocount+1);
 //BA.debugLineNum = 182;BA.debugLine="comboTimer = COMBO_TIMEOUT";
_combotimer = _combo_timeout;
 //BA.debugLineNum = 183;BA.debugLine="comboMultiplier = Min(4.0, 1.0 + (comboCount * 0.";
_combomultiplier = (float) (__c.Min(4.0,1.0+(_combocount*0.25)));
 //BA.debugLineNum = 185;BA.debugLine="clearingLines.Clear";
_clearinglines.Clear();
 //BA.debugLineNum = 186;BA.debugLine="SpawnNextPiece";
_spawnnextpiece();
 //BA.debugLineNum = 187;BA.debugLine="End Sub";
return "";
}
public String  _executeharddrop() throws Exception{
int _targety = 0;
int _dropdistance = 0;
 //BA.debugLineNum = 239;BA.debugLine="Public Sub ExecuteHardDrop";
 //BA.debugLineNum = 240;BA.debugLine="Dim targetY As Int = CalculateDropPosition";
_targety = _calculatedropposition();
 //BA.debugLineNum = 241;BA.debugLine="Dim dropDistance As Int = targetY - activePiece.G";
_dropdistance = (int) (_targety-_activepiece._gety /*int*/ ());
 //BA.debugLineNum = 243;BA.debugLine="activePiece.SetPosition(activePiece.GetX, targetY";
_activepiece._setposition /*String*/ (_activepiece._getx /*int*/ (),_targety);
 //BA.debugLineNum = 244;BA.debugLine="pieceY = targetY";
_piecey = (float) (_targety);
 //BA.debugLineNum = 245;BA.debugLine="pieceTargetY = targetY";
_piecetargety = _targety;
 //BA.debugLineNum = 246;BA.debugLine="currentScore = currentScore + (dropDistance * POI";
_currentscore = (int) (_currentscore+(_dropdistance*_points_hard_drop_row));
 //BA.debugLineNum = 249;BA.debugLine="tickCounter = ticksPerDrop";
_tickcounter = _ticksperdrop;
 //BA.debugLineNum = 250;BA.debugLine="End Sub";
return "";
}
public b4a.example.tetromino  _getactivepiece() throws Exception{
 //BA.debugLineNum = 337;BA.debugLine="Public Sub GetActivePiece As Tetromino";
 //BA.debugLineNum = 338;BA.debugLine="Return activePiece";
if (true) return _activepiece;
 //BA.debugLineNum = 339;BA.debugLine="End Sub";
return null;
}
public float  _getclearanimprogress() throws Exception{
 //BA.debugLineNum = 377;BA.debugLine="Public Sub GetClearAnimProgress As Float";
 //BA.debugLineNum = 378;BA.debugLine="If clearAnimTimer = 0 Then Return 0";
if (_clearanimtimer==0) { 
if (true) return (float) (0);};
 //BA.debugLineNum = 379;BA.debugLine="Return 1.0 - (clearAnimTimer / CLEAR_ANIM_DURATIO";
if (true) return (float) (1.0-(_clearanimtimer/(double)_clear_anim_duration));
 //BA.debugLineNum = 380;BA.debugLine="End Sub";
return 0f;
}
public anywheresoftware.b4a.objects.collections.List  _getclearinglines() throws Exception{
 //BA.debugLineNum = 373;BA.debugLine="Public Sub GetClearingLines As List";
 //BA.debugLineNum = 374;BA.debugLine="Return clearingLines";
if (true) return _clearinglines;
 //BA.debugLineNum = 375;BA.debugLine="End Sub";
return null;
}
public int  _getcombocount() throws Exception{
 //BA.debugLineNum = 357;BA.debugLine="Public Sub GetComboCount As Int";
 //BA.debugLineNum = 358;BA.debugLine="Return comboCount";
if (true) return _combocount;
 //BA.debugLineNum = 359;BA.debugLine="End Sub";
return 0;
}
public float  _getcombomultiplier() throws Exception{
 //BA.debugLineNum = 361;BA.debugLine="Public Sub GetComboMultiplier As Float";
 //BA.debugLineNum = 362;BA.debugLine="Return comboMultiplier";
if (true) return _combomultiplier;
 //BA.debugLineNum = 363;BA.debugLine="End Sub";
return 0f;
}
public b4a.example.grid  _getgrid() throws Exception{
 //BA.debugLineNum = 333;BA.debugLine="Public Sub GetGrid As Grid";
 //BA.debugLineNum = 334;BA.debugLine="Return grid";
if (true) return _grid;
 //BA.debugLineNum = 335;BA.debugLine="End Sub";
return null;
}
public int  _getlevel() throws Exception{
 //BA.debugLineNum = 349;BA.debugLine="Public Sub GetLevel As Int";
 //BA.debugLineNum = 350;BA.debugLine="Return currentLevel";
if (true) return _currentlevel;
 //BA.debugLineNum = 351;BA.debugLine="End Sub";
return 0;
}
public int  _getlinescleared() throws Exception{
 //BA.debugLineNum = 353;BA.debugLine="Public Sub GetLinesCleared As Int";
 //BA.debugLineNum = 354;BA.debugLine="Return totalLinesCleared";
if (true) return _totallinescleared;
 //BA.debugLineNum = 355;BA.debugLine="End Sub";
return 0;
}
public b4a.example.tetromino  _getnextpiece() throws Exception{
 //BA.debugLineNum = 341;BA.debugLine="Public Sub GetNextPiece As Tetromino";
 //BA.debugLineNum = 342;BA.debugLine="Return nextPiece";
if (true) return _nextpiece;
 //BA.debugLineNum = 343;BA.debugLine="End Sub";
return null;
}
public float  _getpieceanimy() throws Exception{
 //BA.debugLineNum = 365;BA.debugLine="Public Sub GetPieceAnimY As Float";
 //BA.debugLineNum = 366;BA.debugLine="Return pieceY";
if (true) return _piecey;
 //BA.debugLineNum = 367;BA.debugLine="End Sub";
return 0f;
}
public int  _getscore() throws Exception{
 //BA.debugLineNum = 345;BA.debugLine="Public Sub GetScore As Int";
 //BA.debugLineNum = 346;BA.debugLine="Return currentScore";
if (true) return _currentscore;
 //BA.debugLineNum = 347;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 51;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 52;BA.debugLine="grid.Initialize(10, 20)";
_grid._initialize /*String*/ (ba,(int) (10),(int) (20));
 //BA.debugLineNum = 53;BA.debugLine="collisionManager.Initialize(grid)";
_collisionmanager._initialize /*String*/ (ba,_grid);
 //BA.debugLineNum = 54;BA.debugLine="pieceFactory.Initialize";
_piecefactory._initialize /*String*/ (ba);
 //BA.debugLineNum = 55;BA.debugLine="soundManager.Initialize";
_soundmanager._initialize /*String*/ (ba);
 //BA.debugLineNum = 56;BA.debugLine="soundManager.LoadAudio";
_soundmanager._loadaudio /*String*/ ();
 //BA.debugLineNum = 58;BA.debugLine="activePiece.Initialize";
_activepiece._initialize /*String*/ (ba);
 //BA.debugLineNum = 59;BA.debugLine="nextPiece.Initialize";
_nextpiece._initialize /*String*/ (ba);
 //BA.debugLineNum = 60;BA.debugLine="clearingLines.Initialize";
_clearinglines.Initialize();
 //BA.debugLineNum = 62;BA.debugLine="ResetGameState";
_resetgamestate();
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public boolean  _isclearinglines() throws Exception{
 //BA.debugLineNum = 369;BA.debugLine="Public Sub IsClearingLines As Boolean";
 //BA.debugLineNum = 370;BA.debugLine="Return clearAnimTimer > 0";
if (true) return _clearanimtimer>0;
 //BA.debugLineNum = 371;BA.debugLine="End Sub";
return false;
}
public String  _lockpiecetogrid() throws Exception{
 //BA.debugLineNum = 278;BA.debugLine="Private Sub LockPieceToGrid";
 //BA.debugLineNum = 279;BA.debugLine="grid.LockPiece(activePiece)";
_grid._lockpiece /*String*/ (_activepiece);
 //BA.debugLineNum = 280;BA.debugLine="End Sub";
return "";
}
public boolean  _movepieceby(int _deltax,int _deltay) throws Exception{
int _newx = 0;
int _newy = 0;
 //BA.debugLineNum = 192;BA.debugLine="Public Sub MovePieceBy(deltaX As Int, deltaY As In";
 //BA.debugLineNum = 193;BA.debugLine="Dim newX As Int = activePiece.GetX + deltaX";
_newx = (int) (_activepiece._getx /*int*/ ()+_deltax);
 //BA.debugLineNum = 194;BA.debugLine="Dim newY As Int = activePiece.GetY + deltaY";
_newy = (int) (_activepiece._gety /*int*/ ()+_deltay);
 //BA.debugLineNum = 196;BA.debugLine="If collisionManager.IsValidPosition(newX, newY, a";
if (_collisionmanager._isvalidposition /*boolean*/ (_newx,_newy,_activepiece._getshape /*int[][]*/ ())) { 
 //BA.debugLineNum = 197;BA.debugLine="activePiece.SetPosition(newX, newY)";
_activepiece._setposition /*String*/ (_newx,_newy);
 //BA.debugLineNum = 198;BA.debugLine="pieceTargetY = newY";
_piecetargety = _newy;
 //BA.debugLineNum = 199;BA.debugLine="If deltaY = 0 Then pieceY = newY  ' Instant hori";
if (_deltay==0) { 
_piecey = (float) (_newy);};
 //BA.debugLineNum = 200;BA.debugLine="Return True";
if (true) return __c.True;
 };
 //BA.debugLineNum = 203;BA.debugLine="Return False";
if (true) return __c.False;
 //BA.debugLineNum = 204;BA.debugLine="End Sub";
return false;
}
public String  _pause() throws Exception{
 //BA.debugLineNum = 88;BA.debugLine="Public Sub Pause";
 //BA.debugLineNum = 89;BA.debugLine="IsPausedState = True";
_ispausedstate = __c.True;
 //BA.debugLineNum = 90;BA.debugLine="soundManager.PauseMusic";
_soundmanager._pausemusic /*String*/ ();
 //BA.debugLineNum = 91;BA.debugLine="End Sub";
return "";
}
public String  _processgravity() throws Exception{
anywheresoftware.b4a.objects.collections.List _completedlines = null;
 //BA.debugLineNum = 148;BA.debugLine="Private Sub ProcessGravity";
 //BA.debugLineNum = 149;BA.debugLine="If Not(MovePieceBy(0, 1)) Then";
if (__c.Not(_movepieceby((int) (0),(int) (1)))) { 
 //BA.debugLineNum = 151;BA.debugLine="LockPieceToGrid";
_lockpiecetogrid();
 //BA.debugLineNum = 153;BA.debugLine="Dim completedLines As List = grid.FindCompletedL";
_completedlines = new anywheresoftware.b4a.objects.collections.List();
_completedlines = _grid._findcompletedlines /*anywheresoftware.b4a.objects.collections.List*/ ();
 //BA.debugLineNum = 154;BA.debugLine="If completedLines.Size > 0 Then";
if (_completedlines.getSize()>0) { 
 //BA.debugLineNum = 156;BA.debugLine="clearingLines = completedLines";
_clearinglines = _completedlines;
 //BA.debugLineNum = 157;BA.debugLine="clearAnimTimer = CLEAR_ANIM_DURATION";
_clearanimtimer = _clear_anim_duration;
 //BA.debugLineNum = 158;BA.debugLine="soundManager.PlayLineClearSound";
_soundmanager._playlineclearsound /*String*/ ();
 }else {
 //BA.debugLineNum = 161;BA.debugLine="comboCount = 0";
_combocount = (int) (0);
 //BA.debugLineNum = 162;BA.debugLine="comboTimer = 0";
_combotimer = (int) (0);
 //BA.debugLineNum = 163;BA.debugLine="comboMultiplier = 1.0";
_combomultiplier = (float) (1.0);
 //BA.debugLineNum = 164;BA.debugLine="SpawnNextPiece";
_spawnnextpiece();
 };
 }else if(_issoftdropactive) { 
 //BA.debugLineNum = 167;BA.debugLine="currentScore = currentScore + POINTS_SOFT_DROP";
_currentscore = (int) (_currentscore+_points_soft_drop);
 };
 //BA.debugLineNum = 169;BA.debugLine="End Sub";
return "";
}
public String  _resetgamestate() throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Private Sub ResetGameState";
 //BA.debugLineNum = 74;BA.debugLine="grid.Clear";
_grid._clear /*String*/ ();
 //BA.debugLineNum = 75;BA.debugLine="currentScore = 0";
_currentscore = (int) (0);
 //BA.debugLineNum = 76;BA.debugLine="currentLevel = 1";
_currentlevel = (int) (1);
 //BA.debugLineNum = 77;BA.debugLine="totalLinesCleared = 0";
_totallinescleared = (int) (0);
 //BA.debugLineNum = 78;BA.debugLine="tickCounter = 0";
_tickcounter = (int) (0);
 //BA.debugLineNum = 79;BA.debugLine="comboCount = 0";
_combocount = (int) (0);
 //BA.debugLineNum = 80;BA.debugLine="comboTimer = 0";
_combotimer = (int) (0);
 //BA.debugLineNum = 81;BA.debugLine="comboMultiplier = 1.0";
_combomultiplier = (float) (1.0);
 //BA.debugLineNum = 82;BA.debugLine="clearAnimTimer = 0";
_clearanimtimer = (int) (0);
 //BA.debugLineNum = 83;BA.debugLine="clearingLines.Clear";
_clearinglines.Clear();
 //BA.debugLineNum = 84;BA.debugLine="UpdateDropSpeed";
_updatedropspeed();
 //BA.debugLineNum = 85;BA.debugLine="nextPiece = pieceFactory.CreateRandomPiece";
_nextpiece = _piecefactory._createrandompiece /*b4a.example.tetromino*/ ();
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public String  _resume() throws Exception{
 //BA.debugLineNum = 93;BA.debugLine="Public Sub Resume";
 //BA.debugLineNum = 94;BA.debugLine="IsPausedState = False";
_ispausedstate = __c.False;
 //BA.debugLineNum = 95;BA.debugLine="End Sub";
return "";
}
public String  _resumeaudio() throws Exception{
 //BA.debugLineNum = 97;BA.debugLine="Public Sub ResumeAudio";
 //BA.debugLineNum = 98;BA.debugLine="soundManager.ResumeMusic";
_soundmanager._resumemusic /*String*/ ();
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public String  _rotatepiece() throws Exception{
int[][] _rotatedshape = null;
int[] _kicks = null;
int _offset = 0;
 //BA.debugLineNum = 206;BA.debugLine="Public Sub RotatePiece";
 //BA.debugLineNum = 207;BA.debugLine="Dim rotatedShape(,) As Int = activePiece.GetRotat";
_rotatedshape = _activepiece._getrotatedshape /*int[][]*/ ();
 //BA.debugLineNum = 210;BA.debugLine="If collisionManager.IsValidPosition(activePiece.G";
if (_collisionmanager._isvalidposition /*boolean*/ (_activepiece._getx /*int*/ (),_activepiece._gety /*int*/ (),_rotatedshape)) { 
 //BA.debugLineNum = 211;BA.debugLine="activePiece.Rotate";
_activepiece._rotate /*String*/ ();
 //BA.debugLineNum = 212;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 216;BA.debugLine="Dim kicks() As Int = Array As Int(1, -1, 2, -2)";
_kicks = new int[]{(int) (1),(int) (-1),(int) (2),(int) (-2)};
 //BA.debugLineNum = 217;BA.debugLine="For Each offset As Int In kicks";
{
final int[] group7 = _kicks;
final int groupLen7 = group7.length
;int index7 = 0;
;
for (; index7 < groupLen7;index7++){
_offset = group7[index7];
 //BA.debugLineNum = 218;BA.debugLine="If collisionManager.IsValidPosition(activePiece.";
if (_collisionmanager._isvalidposition /*boolean*/ ((int) (_activepiece._getx /*int*/ ()+_offset),_activepiece._gety /*int*/ (),_rotatedshape)) { 
 //BA.debugLineNum = 219;BA.debugLine="activePiece.SetPosition(activePiece.GetX + offs";
_activepiece._setposition /*String*/ ((int) (_activepiece._getx /*int*/ ()+_offset),_activepiece._gety /*int*/ ());
 //BA.debugLineNum = 220;BA.debugLine="activePiece.Rotate";
_activepiece._rotate /*String*/ ();
 //BA.debugLineNum = 221;BA.debugLine="pieceY = activePiece.GetY";
_piecey = (float) (_activepiece._gety /*int*/ ());
 //BA.debugLineNum = 222;BA.debugLine="Return";
if (true) return "";
 };
 }
};
 //BA.debugLineNum = 227;BA.debugLine="If collisionManager.IsValidPosition(activePiece.G";
if (_collisionmanager._isvalidposition /*boolean*/ (_activepiece._getx /*int*/ (),(int) (_activepiece._gety /*int*/ ()-1),_rotatedshape)) { 
 //BA.debugLineNum = 228;BA.debugLine="activePiece.SetPosition(activePiece.GetX, active";
_activepiece._setposition /*String*/ (_activepiece._getx /*int*/ (),(int) (_activepiece._gety /*int*/ ()-1));
 //BA.debugLineNum = 229;BA.debugLine="activePiece.Rotate";
_activepiece._rotate /*String*/ ();
 //BA.debugLineNum = 230;BA.debugLine="pieceTargetY = activePiece.GetY";
_piecetargety = _activepiece._gety /*int*/ ();
 //BA.debugLineNum = 231;BA.debugLine="pieceY = activePiece.GetY";
_piecey = (float) (_activepiece._gety /*int*/ ());
 };
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}
public String  _setsoftdrop(boolean _enabled) throws Exception{
 //BA.debugLineNum = 235;BA.debugLine="Public Sub SetSoftDrop(enabled As Boolean)";
 //BA.debugLineNum = 236;BA.debugLine="isSoftDropActive = enabled";
_issoftdropactive = _enabled;
 //BA.debugLineNum = 237;BA.debugLine="End Sub";
return "";
}
public String  _spawnnextpiece() throws Exception{
 //BA.debugLineNum = 265;BA.debugLine="Private Sub SpawnNextPiece";
 //BA.debugLineNum = 266;BA.debugLine="activePiece = nextPiece";
_activepiece = _nextpiece;
 //BA.debugLineNum = 267;BA.debugLine="nextPiece = pieceFactory.CreateRandomPiece";
_nextpiece = _piecefactory._createrandompiece /*b4a.example.tetromino*/ ();
 //BA.debugLineNum = 269;BA.debugLine="activePiece.SetPosition(3, -1)";
_activepiece._setposition /*String*/ ((int) (3),(int) (-1));
 //BA.debugLineNum = 270;BA.debugLine="pieceY = -1";
_piecey = (float) (-1);
 //BA.debugLineNum = 271;BA.debugLine="pieceTargetY = -1";
_piecetargety = (int) (-1);
 //BA.debugLineNum = 273;BA.debugLine="If Not(collisionManager.IsValidPosition(3, -1, ac";
if (__c.Not(_collisionmanager._isvalidposition /*boolean*/ ((int) (3),(int) (-1),_activepiece._getshape /*int[][]*/ ()))) { 
 //BA.debugLineNum = 274;BA.debugLine="TriggerGameOver";
_triggergameover();
 };
 //BA.debugLineNum = 276;BA.debugLine="End Sub";
return "";
}
public String  _startnewgame() throws Exception{
 //BA.debugLineNum = 65;BA.debugLine="Public Sub StartNewGame";
 //BA.debugLineNum = 66;BA.debugLine="ResetGameState";
_resetgamestate();
 //BA.debugLineNum = 67;BA.debugLine="SpawnNextPiece";
_spawnnextpiece();
 //BA.debugLineNum = 68;BA.debugLine="IsActiveState = True";
_isactivestate = __c.True;
 //BA.debugLineNum = 69;BA.debugLine="IsPausedState = False";
_ispausedstate = __c.False;
 //BA.debugLineNum = 70;BA.debugLine="soundManager.StartMusic";
_soundmanager._startmusic /*String*/ ();
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public String  _triggergameover() throws Exception{
 //BA.debugLineNum = 282;BA.debugLine="Private Sub TriggerGameOver";
 //BA.debugLineNum = 283;BA.debugLine="IsActiveState = False";
_isactivestate = __c.False;
 //BA.debugLineNum = 284;BA.debugLine="soundManager.StopMusic";
_soundmanager._stopmusic /*String*/ ();
 //BA.debugLineNum = 285;BA.debugLine="CallSubDelayed2(Tetris, \"HandleGameOver\", Array A";
__c.CallSubDelayed2(ba,(Object)(_tetris.getObject()),"HandleGameOver",(Object)(new Object[]{(Object)(_currentscore)}));
 //BA.debugLineNum = 286;BA.debugLine="End Sub";
return "";
}
public String  _update() throws Exception{
int _dropspeed = 0;
 //BA.debugLineNum = 104;BA.debugLine="Public Sub Update";
 //BA.debugLineNum = 105;BA.debugLine="If Not(IsActiveState) Or IsPausedState Then Retur";
if (__c.Not(_isactivestate) || _ispausedstate) { 
if (true) return "";};
 //BA.debugLineNum = 108;BA.debugLine="If clearAnimTimer > 0 Then";
if (_clearanimtimer>0) { 
 //BA.debugLineNum = 109;BA.debugLine="clearAnimTimer = clearAnimTimer - 1";
_clearanimtimer = (int) (_clearanimtimer-1);
 //BA.debugLineNum = 110;BA.debugLine="If clearAnimTimer = 0 Then";
if (_clearanimtimer==0) { 
 //BA.debugLineNum = 111;BA.debugLine="CompleteClearAnimation";
_completeclearanimation();
 };
 //BA.debugLineNum = 113;BA.debugLine="Return  ' Don't update game logic during animati";
if (true) return "";
 };
 //BA.debugLineNum = 117;BA.debugLine="If comboTimer > 0 Then";
if (_combotimer>0) { 
 //BA.debugLineNum = 118;BA.debugLine="comboTimer = comboTimer - 1";
_combotimer = (int) (_combotimer-1);
 //BA.debugLineNum = 119;BA.debugLine="If comboTimer = 0 Then";
if (_combotimer==0) { 
 //BA.debugLineNum = 121;BA.debugLine="comboCount = 0";
_combocount = (int) (0);
 //BA.debugLineNum = 122;BA.debugLine="comboMultiplier = 1.0";
_combomultiplier = (float) (1.0);
 };
 };
 //BA.debugLineNum = 127;BA.debugLine="If pieceY < pieceTargetY Then";
if (_piecey<_piecetargety) { 
 //BA.debugLineNum = 128;BA.debugLine="pieceY = pieceY + (pieceTargetY - pieceY) * DROP";
_piecey = (float) (_piecey+(_piecetargety-_piecey)*_drop_smoothness);
 //BA.debugLineNum = 129;BA.debugLine="If Abs(pieceTargetY - pieceY) < 0.1 Then pieceY";
if (__c.Abs(_piecetargety-_piecey)<0.1) { 
_piecey = (float) (_piecetargety);};
 };
 //BA.debugLineNum = 132;BA.debugLine="tickCounter = tickCounter + 1";
_tickcounter = (int) (_tickcounter+1);
 //BA.debugLineNum = 133;BA.debugLine="Dim dropSpeed As Int = CalculateCurrentDropSpeed";
_dropspeed = _calculatecurrentdropspeed();
 //BA.debugLineNum = 135;BA.debugLine="If tickCounter >= dropSpeed Then";
if (_tickcounter>=_dropspeed) { 
 //BA.debugLineNum = 136;BA.debugLine="tickCounter = 0";
_tickcounter = (int) (0);
 //BA.debugLineNum = 137;BA.debugLine="ProcessGravity";
_processgravity();
 };
 //BA.debugLineNum = 139;BA.debugLine="End Sub";
return "";
}
public String  _updatedropspeed() throws Exception{
 //BA.debugLineNum = 326;BA.debugLine="Private Sub UpdateDropSpeed";
 //BA.debugLineNum = 327;BA.debugLine="ticksPerDrop = Max(5, BASE_TICKS_PER_DROP - (curr";
_ticksperdrop = (int) (__c.Max(5,_base_ticks_per_drop-(_currentlevel*2)));
 //BA.debugLineNum = 328;BA.debugLine="End Sub";
return "";
}
public String  _updatelevel() throws Exception{
int _newlevel = 0;
 //BA.debugLineNum = 318;BA.debugLine="Private Sub UpdateLevel";
 //BA.debugLineNum = 319;BA.debugLine="Dim newLevel As Int = (totalLinesCleared / 10) +";
_newlevel = (int) ((_totallinescleared/(double)10)+1);
 //BA.debugLineNum = 320;BA.debugLine="If newLevel > currentLevel Then";
if (_newlevel>_currentlevel) { 
 //BA.debugLineNum = 321;BA.debugLine="currentLevel = newLevel";
_currentlevel = _newlevel;
 //BA.debugLineNum = 322;BA.debugLine="UpdateDropSpeed";
_updatedropspeed();
 };
 //BA.debugLineNum = 324;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
