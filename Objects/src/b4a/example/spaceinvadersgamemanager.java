package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class spaceinvadersgamemanager extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.spaceinvadersgamemanager");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.spaceinvadersgamemanager.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.drawable.CanvasWrapper _gamecanvas = null;
public anywheresoftware.b4a.objects.PanelWrapper _gamepanel = null;
public b4a.example.soundmanager _soundmanager = null;
public boolean _isactive = false;
public boolean _ispausedstate = false;
public int _currentscore = 0;
public int _currentwave = 0;
public int _highscore = 0;
public int _lives = 0;
public float _playerx = 0f;
public float _playertargetx = 0f;
public int _playery = 0;
public int _playerwidth = 0;
public int _playerheight = 0;
public float _player_smoothness = 0f;
public anywheresoftware.b4a.objects.collections.List _invaders = null;
public int _invaderdirection = 0;
public int _invaderspeed = 0;
public boolean _isbosswave = false;
public boolean _bossactive = false;
public float _bossx = 0f;
public int _bossy = 0;
public int _bosswidth = 0;
public int _bossheight = 0;
public int _bosshealth = 0;
public int _bossmaxhealth = 0;
public int _bosspattern = 0;
public int _combocount = 0;
public int _combotimer = 0;
public float _combomultiplier = 0f;
public int _combo_timeout = 0;
public anywheresoftware.b4a.objects.collections.List _powerups = null;
public String _activepowerup = "";
public int _poweruptimer = 0;
public int _powerup_duration = 0;
public anywheresoftware.b4a.objects.collections.List _particles = null;
public anywheresoftware.b4a.objects.collections.List _playerbullets = null;
public anywheresoftware.b4a.objects.collections.List _enemybullets = null;
public anywheresoftware.b4a.objects.collections.List _shields = null;
public int _framecounter = 0;
public int _invadermoveinterval = 0;
public int _color_background = 0;
public int _color_player = 0;
public int _color_invader = 0;
public int _color_boss = 0;
public int _color_powerup = 0;
public int _score_invader = 0;
public int _score_boss = 0;
public int _initial_lives = 0;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _addcombo() throws Exception{
 //BA.debugLineNum = 431;BA.debugLine="Private Sub AddCombo";
 //BA.debugLineNum = 432;BA.debugLine="comboCount = comboCount + 1";
_combocount = (int) (_combocount+1);
 //BA.debugLineNum = 433;BA.debugLine="comboTimer = COMBO_TIMEOUT";
_combotimer = _combo_timeout;
 //BA.debugLineNum = 434;BA.debugLine="comboMultiplier = Min(5.0, 1.0 + (comboCount * 0.";
_combomultiplier = (float) (__c.Min(5.0,1.0+(_combocount*0.2)));
 //BA.debugLineNum = 435;BA.debugLine="End Sub";
return "";
}
public String  _checkcollisions() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _b = null;
int _bx = 0;
int _by = 0;
boolean _hit = false;
anywheresoftware.b4a.objects.collections.Map _inv = null;
int _ix = 0;
int _iy = 0;
anywheresoftware.b4a.objects.collections.Map _bb = null;
float _bxx = 0f;
int _byy = 0;
anywheresoftware.b4a.objects.collections.Map _p = null;
int _px = 0;
int _py = 0;
anywheresoftware.b4a.objects.collections.Map _s = null;
 //BA.debugLineNum = 354;BA.debugLine="Private Sub CheckCollisions";
 //BA.debugLineNum = 356;BA.debugLine="For i = playerBullets.Size - 1 To 0 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (0);
_i = (int) (_playerbullets.getSize()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 357;BA.debugLine="Dim b As Map = playerBullets.Get(i)";
_b = new anywheresoftware.b4a.objects.collections.Map();
_b = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_playerbullets.Get(_i)));
 //BA.debugLineNum = 358;BA.debugLine="Dim bx As Int = b.Get(\"x\")";
_bx = (int)(BA.ObjectToNumber(_b.Get((Object)("x"))));
 //BA.debugLineNum = 359;BA.debugLine="Dim by As Int = b.Get(\"y\")";
_by = (int)(BA.ObjectToNumber(_b.Get((Object)("y"))));
 //BA.debugLineNum = 360;BA.debugLine="Dim hit As Boolean = False";
_hit = __c.False;
 //BA.debugLineNum = 362;BA.debugLine="If isBossWave And bossActive Then";
if (_isbosswave && _bossactive) { 
 //BA.debugLineNum = 363;BA.debugLine="If bx >= bossX And bx <= bossX + bossWidth And";
if (_bx>=_bossx && _bx<=_bossx+_bosswidth && _by>=_bossy && _by<=_bossy+_bossheight) { 
 //BA.debugLineNum = 364;BA.debugLine="bossHealth = bossHealth - 1";
_bosshealth = (int) (_bosshealth-1);
 //BA.debugLineNum = 365;BA.debugLine="CreateExplosion(bx, by, COLOR_BOSS)";
_createexplosion((float) (_bx),(float) (_by),_color_boss);
 //BA.debugLineNum = 366;BA.debugLine="AddCombo";
_addcombo();
 //BA.debugLineNum = 367;BA.debugLine="hit = True";
_hit = __c.True;
 //BA.debugLineNum = 368;BA.debugLine="If bossHealth <= 0 Then";
if (_bosshealth<=0) { 
 //BA.debugLineNum = 369;BA.debugLine="bossActive = False";
_bossactive = __c.False;
 //BA.debugLineNum = 370;BA.debugLine="currentScore = currentScore + SCORE_BOSS * co";
_currentscore = (int) (_currentscore+_score_boss*_combomultiplier);
 //BA.debugLineNum = 371;BA.debugLine="CreateExplosion(bossX + bossWidth/2, bossY +";
_createexplosion((float) (_bossx+_bosswidth/(double)2),(float) (_bossy+_bossheight/(double)2),_color_boss);
 //BA.debugLineNum = 372;BA.debugLine="SpawnPowerup(bossX + bossWidth/2, bossY + bos";
_spawnpowerup((float) (_bossx+_bosswidth/(double)2),(float) (_bossy+_bossheight/(double)2));
 };
 };
 }else {
 //BA.debugLineNum = 376;BA.debugLine="For Each inv As Map In invaders";
_inv = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group20 = _invaders;
final int groupLen20 = group20.getSize()
;int index20 = 0;
;
for (; index20 < groupLen20;index20++){
_inv = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group20.Get(index20)));
 //BA.debugLineNum = 377;BA.debugLine="If inv.Get(\"alive\") Then";
if (BA.ObjectToBoolean(_inv.Get((Object)("alive")))) { 
 //BA.debugLineNum = 378;BA.debugLine="Dim ix As Int = inv.Get(\"x\")";
_ix = (int)(BA.ObjectToNumber(_inv.Get((Object)("x"))));
 //BA.debugLineNum = 379;BA.debugLine="Dim iy As Int = inv.Get(\"y\")";
_iy = (int)(BA.ObjectToNumber(_inv.Get((Object)("y"))));
 //BA.debugLineNum = 380;BA.debugLine="If bx >= ix And bx <= ix + 30 And by >= iy An";
if (_bx>=_ix && _bx<=_ix+30 && _by>=_iy && _by<=_iy+25) { 
 //BA.debugLineNum = 381;BA.debugLine="inv.Put(\"alive\", False)";
_inv.Put((Object)("alive"),(Object)(__c.False));
 //BA.debugLineNum = 382;BA.debugLine="currentScore = currentScore + SCORE_INVADER";
_currentscore = (int) (_currentscore+_score_invader*_combomultiplier);
 //BA.debugLineNum = 383;BA.debugLine="CreateExplosion(ix + 15, iy + 12, COLOR_INVA";
_createexplosion((float) (_ix+15),(float) (_iy+12),_color_invader);
 //BA.debugLineNum = 384;BA.debugLine="AddCombo";
_addcombo();
 //BA.debugLineNum = 385;BA.debugLine="If Rnd(0, 100) < 20 Then SpawnPowerup(ix + 1";
if (__c.Rnd((int) (0),(int) (100))<20) { 
_spawnpowerup((float) (_ix+15),(float) (_iy+12));};
 //BA.debugLineNum = 386;BA.debugLine="soundManager.PlayLineClearSound";
_soundmanager._playlineclearsound /*String*/ ();
 //BA.debugLineNum = 387;BA.debugLine="hit = True";
_hit = __c.True;
 //BA.debugLineNum = 388;BA.debugLine="Exit";
if (true) break;
 };
 };
 }
};
 };
 //BA.debugLineNum = 394;BA.debugLine="If hit Then playerBullets.RemoveAt(i)";
if (_hit) { 
_playerbullets.RemoveAt(_i);};
 }
};
 //BA.debugLineNum = 398;BA.debugLine="For i = enemyBullets.Size - 1 To 0 Step -1";
{
final int step39 = -1;
final int limit39 = (int) (0);
_i = (int) (_enemybullets.getSize()-1) ;
for (;_i >= limit39 ;_i = _i + step39 ) {
 //BA.debugLineNum = 399;BA.debugLine="Dim bb As Map = enemyBullets.Get(i)";
_bb = new anywheresoftware.b4a.objects.collections.Map();
_bb = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_enemybullets.Get(_i)));
 //BA.debugLineNum = 400;BA.debugLine="Dim bxx As Float = bb.Get(\"x\")";
_bxx = (float)(BA.ObjectToNumber(_bb.Get((Object)("x"))));
 //BA.debugLineNum = 401;BA.debugLine="Dim byy As Int = bb.Get(\"y\")";
_byy = (int)(BA.ObjectToNumber(_bb.Get((Object)("y"))));
 //BA.debugLineNum = 402;BA.debugLine="If bx >= playerX And bxx <= playerX + playerWidt";
if (_bx>=_playerx && _bxx<=_playerx+_playerwidth && _byy>=_playery && _byy<=_playery+_playerheight) { 
 //BA.debugLineNum = 403;BA.debugLine="lives = lives - 1";
_lives = (int) (_lives-1);
 //BA.debugLineNum = 404;BA.debugLine="CreateExplosion(playerX + playerWidth/2, player";
_createexplosion((float) (_playerx+_playerwidth/(double)2),(float) (_playery+_playerheight/(double)2),_color_player);
 //BA.debugLineNum = 405;BA.debugLine="comboCount = 0";
_combocount = (int) (0);
 //BA.debugLineNum = 406;BA.debugLine="comboMultiplier = 1.0";
_combomultiplier = (float) (1.0);
 //BA.debugLineNum = 407;BA.debugLine="enemyBullets.RemoveAt(i)";
_enemybullets.RemoveAt(_i);
 //BA.debugLineNum = 408;BA.debugLine="If lives <= 0 Then TriggerGameOver";
if (_lives<=0) { 
_triggergameover();};
 };
 }
};
 //BA.debugLineNum = 413;BA.debugLine="For i = powerups.Size - 1 To 0 Step -1";
{
final int step52 = -1;
final int limit52 = (int) (0);
_i = (int) (_powerups.getSize()-1) ;
for (;_i >= limit52 ;_i = _i + step52 ) {
 //BA.debugLineNum = 414;BA.debugLine="Dim p As Map = powerups.Get(i)";
_p = new anywheresoftware.b4a.objects.collections.Map();
_p = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_powerups.Get(_i)));
 //BA.debugLineNum = 415;BA.debugLine="Dim px As Int = p.Get(\"x\")";
_px = (int)(BA.ObjectToNumber(_p.Get((Object)("x"))));
 //BA.debugLineNum = 416;BA.debugLine="Dim py As Int = p.Get(\"y\")";
_py = (int)(BA.ObjectToNumber(_p.Get((Object)("y"))));
 //BA.debugLineNum = 417;BA.debugLine="If px >= playerX And px <= playerX + playerWidth";
if (_px>=_playerx && _px<=_playerx+_playerwidth && _py>=_playery && _py<=_playery+_playerheight) { 
 //BA.debugLineNum = 418;BA.debugLine="activePowerup = p.Get(\"type\")";
_activepowerup = BA.ObjectToString(_p.Get((Object)("type")));
 //BA.debugLineNum = 419;BA.debugLine="powerupTimer = POWERUP_DURATION";
_poweruptimer = _powerup_duration;
 //BA.debugLineNum = 420;BA.debugLine="If activePowerup = \"SHIELD\" Then";
if ((_activepowerup).equals("SHIELD")) { 
 //BA.debugLineNum = 421;BA.debugLine="For Each s As Map In shields";
_s = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group60 = _shields;
final int groupLen60 = group60.getSize()
;int index60 = 0;
;
for (; index60 < groupLen60;index60++){
_s = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group60.Get(index60)));
 //BA.debugLineNum = 422;BA.debugLine="s.Put(\"health\", 10)";
_s.Put((Object)("health"),(Object)(10));
 }
};
 };
 //BA.debugLineNum = 425;BA.debugLine="powerups.RemoveAt(i)";
_powerups.RemoveAt(_i);
 //BA.debugLineNum = 426;BA.debugLine="soundManager.PlayLineClearSound";
_soundmanager._playlineclearsound /*String*/ ();
 };
 }
};
 //BA.debugLineNum = 429;BA.debugLine="End Sub";
return "";
}
public String  _checkwincondition() throws Exception{
boolean _alldead = false;
anywheresoftware.b4a.objects.collections.Map _inv = null;
 //BA.debugLineNum = 460;BA.debugLine="Private Sub CheckWinCondition";
 //BA.debugLineNum = 461;BA.debugLine="If isBossWave Then";
if (_isbosswave) { 
 //BA.debugLineNum = 462;BA.debugLine="If Not(bossActive) Then NextWave";
if (__c.Not(_bossactive)) { 
_nextwave();};
 }else {
 //BA.debugLineNum = 464;BA.debugLine="Dim allDead As Boolean = True";
_alldead = __c.True;
 //BA.debugLineNum = 465;BA.debugLine="For Each inv As Map In invaders";
_inv = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group5 = _invaders;
final int groupLen5 = group5.getSize()
;int index5 = 0;
;
for (; index5 < groupLen5;index5++){
_inv = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group5.Get(index5)));
 //BA.debugLineNum = 466;BA.debugLine="If inv.Get(\"alive\") Then";
if (BA.ObjectToBoolean(_inv.Get((Object)("alive")))) { 
 //BA.debugLineNum = 467;BA.debugLine="allDead = False";
_alldead = __c.False;
 //BA.debugLineNum = 468;BA.debugLine="If inv.Get(\"y\") >= playerY - 30 Then TriggerGa";
if ((double)(BA.ObjectToNumber(_inv.Get((Object)("y"))))>=_playery-30) { 
_triggergameover();};
 };
 }
};
 //BA.debugLineNum = 471;BA.debugLine="If allDead Then NextWave";
if (_alldead) { 
_nextwave();};
 };
 //BA.debugLineNum = 473;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private gameCanvas As Canvas";
_gamecanvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Private gamePanel As Panel";
_gamepanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 8;BA.debugLine="Private soundManager As SoundManager";
_soundmanager = new b4a.example.soundmanager();
 //BA.debugLineNum = 10;BA.debugLine="Public IsActive As Boolean";
_isactive = false;
 //BA.debugLineNum = 11;BA.debugLine="Public IsPausedState As Boolean";
_ispausedstate = false;
 //BA.debugLineNum = 12;BA.debugLine="Private currentScore As Int";
_currentscore = 0;
 //BA.debugLineNum = 13;BA.debugLine="Private currentWave As Int";
_currentwave = 0;
 //BA.debugLineNum = 14;BA.debugLine="Private highScore As Int";
_highscore = 0;
 //BA.debugLineNum = 15;BA.debugLine="Private lives As Int";
_lives = 0;
 //BA.debugLineNum = 18;BA.debugLine="Private playerX As Float";
_playerx = 0f;
 //BA.debugLineNum = 19;BA.debugLine="Private playerTargetX As Float";
_playertargetx = 0f;
 //BA.debugLineNum = 20;BA.debugLine="Private playerY As Int";
_playery = 0;
 //BA.debugLineNum = 21;BA.debugLine="Private playerWidth As Int = 40";
_playerwidth = (int) (40);
 //BA.debugLineNum = 22;BA.debugLine="Private playerHeight As Int = 30";
_playerheight = (int) (30);
 //BA.debugLineNum = 23;BA.debugLine="Private Const PLAYER_SMOOTHNESS As Float = 0.25";
_player_smoothness = (float) (0.25);
 //BA.debugLineNum = 26;BA.debugLine="Private invaders As List";
_invaders = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 27;BA.debugLine="Private invaderDirection As Int = 1";
_invaderdirection = (int) (1);
 //BA.debugLineNum = 28;BA.debugLine="Private invaderSpeed As Int = 4";
_invaderspeed = (int) (4);
 //BA.debugLineNum = 31;BA.debugLine="Private isBossWave As Boolean";
_isbosswave = false;
 //BA.debugLineNum = 32;BA.debugLine="Private bossActive As Boolean";
_bossactive = false;
 //BA.debugLineNum = 33;BA.debugLine="Private bossX As Float";
_bossx = 0f;
 //BA.debugLineNum = 34;BA.debugLine="Private bossY As Int";
_bossy = 0;
 //BA.debugLineNum = 35;BA.debugLine="Private bossWidth As Int = 100";
_bosswidth = (int) (100);
 //BA.debugLineNum = 36;BA.debugLine="Private bossHeight As Int = 70";
_bossheight = (int) (70);
 //BA.debugLineNum = 37;BA.debugLine="Private bossHealth As Int";
_bosshealth = 0;
 //BA.debugLineNum = 38;BA.debugLine="Private bossMaxHealth As Int = 30";
_bossmaxhealth = (int) (30);
 //BA.debugLineNum = 39;BA.debugLine="Private bossPattern As Int = 0";
_bosspattern = (int) (0);
 //BA.debugLineNum = 42;BA.debugLine="Private comboCount As Int";
_combocount = 0;
 //BA.debugLineNum = 43;BA.debugLine="Private comboTimer As Int";
_combotimer = 0;
 //BA.debugLineNum = 44;BA.debugLine="Private comboMultiplier As Float = 1.0";
_combomultiplier = (float) (1.0);
 //BA.debugLineNum = 45;BA.debugLine="Private Const COMBO_TIMEOUT As Int = 90";
_combo_timeout = (int) (90);
 //BA.debugLineNum = 48;BA.debugLine="Private powerups As List";
_powerups = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 49;BA.debugLine="Private activePowerup As String = \"NONE\"";
_activepowerup = "NONE";
 //BA.debugLineNum = 50;BA.debugLine="Private powerupTimer As Int = 0";
_poweruptimer = (int) (0);
 //BA.debugLineNum = 51;BA.debugLine="Private Const POWERUP_DURATION As Int = 200";
_powerup_duration = (int) (200);
 //BA.debugLineNum = 54;BA.debugLine="Private particles As List";
_particles = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 56;BA.debugLine="Private playerBullets As List";
_playerbullets = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 57;BA.debugLine="Private enemyBullets As List";
_enemybullets = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 58;BA.debugLine="Private shields As List";
_shields = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 60;BA.debugLine="Private frameCounter As Int";
_framecounter = 0;
 //BA.debugLineNum = 61;BA.debugLine="Private invaderMoveInterval As Int = 15";
_invadermoveinterval = (int) (15);
 //BA.debugLineNum = 64;BA.debugLine="Private Const COLOR_BACKGROUND As Int = 0xFF28282";
_color_background = ((int)0xff282828);
 //BA.debugLineNum = 65;BA.debugLine="Private Const COLOR_PLAYER As Int = 0xFF00ff00";
_color_player = ((int)0xff00ff00);
 //BA.debugLineNum = 66;BA.debugLine="Private Const COLOR_INVADER As Int = 0xFFff00ff";
_color_invader = ((int)0xffff00ff);
 //BA.debugLineNum = 67;BA.debugLine="Private Const COLOR_BOSS As Int = 0xFFff3333";
_color_boss = ((int)0xffff3333);
 //BA.debugLineNum = 68;BA.debugLine="Private Const COLOR_POWERUP As Int = 0xFFffaa00";
_color_powerup = ((int)0xffffaa00);
 //BA.debugLineNum = 69;BA.debugLine="Private Const SCORE_INVADER As Int = 10";
_score_invader = (int) (10);
 //BA.debugLineNum = 70;BA.debugLine="Private Const SCORE_BOSS As Int = 500";
_score_boss = (int) (500);
 //BA.debugLineNum = 71;BA.debugLine="Private Const INITIAL_LIVES As Int = 3";
_initial_lives = (int) (3);
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public String  _createexplosion(float _x,float _y,int _color) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _p = null;
 //BA.debugLineNum = 437;BA.debugLine="Private Sub CreateExplosion(x As Float, y As Float";
 //BA.debugLineNum = 438;BA.debugLine="For i = 0 To 10";
{
final int step1 = 1;
final int limit1 = (int) (10);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 439;BA.debugLine="Dim p As Map";
_p = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 440;BA.debugLine="p.Initialize";
_p.Initialize();
 //BA.debugLineNum = 441;BA.debugLine="p.Put(\"x\", x)";
_p.Put((Object)("x"),(Object)(_x));
 //BA.debugLineNum = 442;BA.debugLine="p.Put(\"y\", y)";
_p.Put((Object)("y"),(Object)(_y));
 //BA.debugLineNum = 443;BA.debugLine="p.Put(\"vx\", (Rnd(-10, 11) / 10.0) * 3)";
_p.Put((Object)("vx"),(Object)((__c.Rnd((int) (-10),(int) (11))/(double)10.0)*3));
 //BA.debugLineNum = 444;BA.debugLine="p.Put(\"vy\", (Rnd(-10, 11) / 10.0) * 3)";
_p.Put((Object)("vy"),(Object)((__c.Rnd((int) (-10),(int) (11))/(double)10.0)*3));
 //BA.debugLineNum = 445;BA.debugLine="p.Put(\"life\", Rnd(15, 30))";
_p.Put((Object)("life"),(Object)(__c.Rnd((int) (15),(int) (30))));
 //BA.debugLineNum = 446;BA.debugLine="p.Put(\"color\", color)";
_p.Put((Object)("color"),(Object)(_color));
 //BA.debugLineNum = 447;BA.debugLine="particles.Add(p)";
_particles.Add((Object)(_p.getObject()));
 }
};
 //BA.debugLineNum = 449;BA.debugLine="End Sub";
return "";
}
public String  _draw() throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
int _hpwidth = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _hprect = null;
anywheresoftware.b4a.objects.collections.Map _inv = null;
anywheresoftware.b4a.objects.collections.Map _s = null;
int _alpha = 0;
int _color = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _outline = null;
anywheresoftware.b4a.objects.collections.Map _b = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _br = null;
anywheresoftware.b4a.objects.collections.Map _p = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _pr = null;
int _life = 0;
int _particlecolor = 0;
float _px = 0f;
float _py = 0f;
 //BA.debugLineNum = 510;BA.debugLine="Public Sub Draw";
 //BA.debugLineNum = 511;BA.debugLine="gameCanvas.DrawColor(COLOR_BACKGROUND)";
_gamecanvas.DrawColor(_color_background);
 //BA.debugLineNum = 514;BA.debugLine="If isBossWave And bossActive Then";
if (_isbosswave && _bossactive) { 
 //BA.debugLineNum = 515;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 516;BA.debugLine="rect.Initialize(bossX, bossY, bossX + bossWidth,";
_rect.Initialize((int) (_bossx),_bossy,(int) (_bossx+_bosswidth),(int) (_bossy+_bossheight));
 //BA.debugLineNum = 517;BA.debugLine="gameCanvas.DrawRect(rect, COLOR_BOSS, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_rect.getObject()),_color_boss,__c.True,(float) (1));
 //BA.debugLineNum = 519;BA.debugLine="Dim hpWidth As Int = (bossWidth * bossHealth) /";
_hpwidth = (int) ((_bosswidth*_bosshealth)/(double)_bossmaxhealth);
 //BA.debugLineNum = 520;BA.debugLine="Dim hpRect As Rect";
_hprect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 521;BA.debugLine="hpRect.Initialize(bossX, bossY - 8, bossX + hpWi";
_hprect.Initialize((int) (_bossx),(int) (_bossy-8),(int) (_bossx+_hpwidth),(int) (_bossy-4));
 //BA.debugLineNum = 522;BA.debugLine="gameCanvas.DrawRect(hpRect, Colors.Red, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_hprect.getObject()),__c.Colors.Red,__c.True,(float) (1));
 }else {
 //BA.debugLineNum = 524;BA.debugLine="For Each inv As Map In invaders";
_inv = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group11 = _invaders;
final int groupLen11 = group11.getSize()
;int index11 = 0;
;
for (; index11 < groupLen11;index11++){
_inv = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group11.Get(index11)));
 //BA.debugLineNum = 525;BA.debugLine="If inv.Get(\"alive\") Then";
if (BA.ObjectToBoolean(_inv.Get((Object)("alive")))) { 
 //BA.debugLineNum = 526;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 527;BA.debugLine="rect.Initialize(inv.Get(\"x\"), inv.Get(\"y\"), in";
_rect.Initialize((int)(BA.ObjectToNumber(_inv.Get((Object)("x")))),(int)(BA.ObjectToNumber(_inv.Get((Object)("y")))),(int) ((double)(BA.ObjectToNumber(_inv.Get((Object)("x"))))+30),(int) ((double)(BA.ObjectToNumber(_inv.Get((Object)("y"))))+25));
 //BA.debugLineNum = 528;BA.debugLine="gameCanvas.DrawRect(rect, COLOR_INVADER, True,";
_gamecanvas.DrawRect((android.graphics.Rect)(_rect.getObject()),_color_invader,__c.True,(float) (1));
 };
 }
};
 };
 //BA.debugLineNum = 534;BA.debugLine="For Each s As Map In shields";
_s = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group19 = _shields;
final int groupLen19 = group19.getSize()
;int index19 = 0;
;
for (; index19 < groupLen19;index19++){
_s = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group19.Get(index19)));
 //BA.debugLineNum = 535;BA.debugLine="Dim alpha As Int = Min(255, s.Get(\"health\") * 25";
_alpha = (int) (__c.Min(255,(double)(BA.ObjectToNumber(_s.Get((Object)("health"))))*25));
 //BA.debugLineNum = 536;BA.debugLine="Dim color As Int = Colors.ARGB(alpha, 0, 255, 25";
_color = __c.Colors.ARGB(_alpha,(int) (0),(int) (255),(int) (255));
 //BA.debugLineNum = 537;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 538;BA.debugLine="rect.Initialize(s.Get(\"x\"), s.Get(\"y\"), s.Get(\"x";
_rect.Initialize((int)(BA.ObjectToNumber(_s.Get((Object)("x")))),(int)(BA.ObjectToNumber(_s.Get((Object)("y")))),(int) ((double)(BA.ObjectToNumber(_s.Get((Object)("x"))))+60),(int) ((double)(BA.ObjectToNumber(_s.Get((Object)("y"))))+30));
 //BA.debugLineNum = 539;BA.debugLine="gameCanvas.DrawRect(rect, color, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_rect.getObject()),_color,__c.True,(float) (1));
 }
};
 //BA.debugLineNum = 543;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 544;BA.debugLine="rect.Initialize(playerX, playerY, playerX + playe";
_rect.Initialize((int) (_playerx),_playery,(int) (_playerx+_playerwidth),(int) (_playery+_playerheight));
 //BA.debugLineNum = 545;BA.debugLine="gameCanvas.DrawRect(rect, COLOR_PLAYER, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_rect.getObject()),_color_player,__c.True,(float) (1));
 //BA.debugLineNum = 546;BA.debugLine="If activePowerup = \"SHIELD\" Then";
if ((_activepowerup).equals("SHIELD")) { 
 //BA.debugLineNum = 547;BA.debugLine="Dim outline As Rect";
_outline = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 548;BA.debugLine="outline.Initialize(playerX - 3, playerY - 3, pla";
_outline.Initialize((int) (_playerx-3),(int) (_playery-3),(int) (_playerx+_playerwidth+3),(int) (_playery+_playerheight+3));
 //BA.debugLineNum = 549;BA.debugLine="gameCanvas.DrawRect(outline, Colors.Cyan, False,";
_gamecanvas.DrawRect((android.graphics.Rect)(_outline.getObject()),__c.Colors.Cyan,__c.False,(float) (2));
 };
 //BA.debugLineNum = 553;BA.debugLine="For Each b As Map In playerBullets";
_b = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group34 = _playerbullets;
final int groupLen34 = group34.getSize()
;int index34 = 0;
;
for (; index34 < groupLen34;index34++){
_b = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group34.Get(index34)));
 //BA.debugLineNum = 554;BA.debugLine="Dim br As Rect";
_br = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 555;BA.debugLine="br.Initialize(b.Get(\"x\") - 2, b.Get(\"y\") - 6, b.";
_br.Initialize((int) ((double)(BA.ObjectToNumber(_b.Get((Object)("x"))))-2),(int) ((double)(BA.ObjectToNumber(_b.Get((Object)("y"))))-6),(int) ((double)(BA.ObjectToNumber(_b.Get((Object)("x"))))+2),(int) ((double)(BA.ObjectToNumber(_b.Get((Object)("y"))))+6));
 //BA.debugLineNum = 556;BA.debugLine="gameCanvas.DrawRect(br, Colors.Yellow, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_br.getObject()),__c.Colors.Yellow,__c.True,(float) (1));
 }
};
 //BA.debugLineNum = 558;BA.debugLine="For Each b As Map In enemyBullets";
_b = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group39 = _enemybullets;
final int groupLen39 = group39.getSize()
;int index39 = 0;
;
for (; index39 < groupLen39;index39++){
_b = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group39.Get(index39)));
 //BA.debugLineNum = 559;BA.debugLine="Dim br As Rect";
_br = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 560;BA.debugLine="br.Initialize(b.Get(\"x\") - 2, b.Get(\"y\") - 6, b.";
_br.Initialize((int) ((double)(BA.ObjectToNumber(_b.Get((Object)("x"))))-2),(int) ((double)(BA.ObjectToNumber(_b.Get((Object)("y"))))-6),(int) ((double)(BA.ObjectToNumber(_b.Get((Object)("x"))))+2),(int) ((double)(BA.ObjectToNumber(_b.Get((Object)("y"))))+6));
 //BA.debugLineNum = 561;BA.debugLine="gameCanvas.DrawRect(br, Colors.Red, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_br.getObject()),__c.Colors.Red,__c.True,(float) (1));
 }
};
 //BA.debugLineNum = 565;BA.debugLine="For Each p As Map In powerups";
_p = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group44 = _powerups;
final int groupLen44 = group44.getSize()
;int index44 = 0;
;
for (; index44 < groupLen44;index44++){
_p = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group44.Get(index44)));
 //BA.debugLineNum = 566;BA.debugLine="Dim pr As Rect";
_pr = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 567;BA.debugLine="pr.Initialize(p.Get(\"x\") - 8, p.Get(\"y\") - 8, p.";
_pr.Initialize((int) ((double)(BA.ObjectToNumber(_p.Get((Object)("x"))))-8),(int) ((double)(BA.ObjectToNumber(_p.Get((Object)("y"))))-8),(int) ((double)(BA.ObjectToNumber(_p.Get((Object)("x"))))+8),(int) ((double)(BA.ObjectToNumber(_p.Get((Object)("y"))))+8));
 //BA.debugLineNum = 568;BA.debugLine="gameCanvas.DrawRect(pr, COLOR_POWERUP, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_pr.getObject()),_color_powerup,__c.True,(float) (1));
 }
};
 //BA.debugLineNum = 572;BA.debugLine="For Each p As Map In particles";
_p = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group49 = _particles;
final int groupLen49 = group49.getSize()
;int index49 = 0;
;
for (; index49 < groupLen49;index49++){
_p = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group49.Get(index49)));
 //BA.debugLineNum = 573;BA.debugLine="Dim life As Int = p.Get(\"life\")";
_life = (int)(BA.ObjectToNumber(_p.Get((Object)("life"))));
 //BA.debugLineNum = 574;BA.debugLine="Dim alpha As Int = Min(255, life * 8)";
_alpha = (int) (__c.Min(255,_life*8));
 //BA.debugLineNum = 575;BA.debugLine="Dim color As Int = p.Get(\"color\")";
_color = (int)(BA.ObjectToNumber(_p.Get((Object)("color"))));
 //BA.debugLineNum = 576;BA.debugLine="Dim particleColor As Int = Colors.ARGB(alpha, _";
_particlecolor = __c.Colors.ARGB(_alpha,__c.Bit.And(__c.Bit.UnsignedShiftRight(_color,(int) (16)),((int)0xff)),__c.Bit.And(__c.Bit.UnsignedShiftRight(_color,(int) (8)),((int)0xff)),__c.Bit.And(_color,((int)0xff)));
 //BA.debugLineNum = 580;BA.debugLine="Dim px As Float = p.Get(\"x\")";
_px = (float)(BA.ObjectToNumber(_p.Get((Object)("x"))));
 //BA.debugLineNum = 581;BA.debugLine="Dim py As Float = p.Get(\"y\")";
_py = (float)(BA.ObjectToNumber(_p.Get((Object)("y"))));
 //BA.debugLineNum = 582;BA.debugLine="Dim pr As Rect";
_pr = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 583;BA.debugLine="pr.Initialize(px - 2, py - 2, px + 2, py + 2)";
_pr.Initialize((int) (_px-2),(int) (_py-2),(int) (_px+2),(int) (_py+2));
 //BA.debugLineNum = 584;BA.debugLine="gameCanvas.DrawRect(pr, particleColor, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_pr.getObject()),_particlecolor,__c.True,(float) (1));
 }
};
 //BA.debugLineNum = 587;BA.debugLine="gamePanel.Invalidate";
_gamepanel.Invalidate();
 //BA.debugLineNum = 588;BA.debugLine="End Sub";
return "";
}
public String  _firebossbullet(float _x,int _y,float _vx,int _offsetx) throws Exception{
anywheresoftware.b4a.objects.collections.Map _bullet = null;
 //BA.debugLineNum = 241;BA.debugLine="Private Sub FireBossBullet(x As Float, y As Int, v";
 //BA.debugLineNum = 242;BA.debugLine="Dim bullet As Map";
_bullet = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 243;BA.debugLine="bullet.Initialize";
_bullet.Initialize();
 //BA.debugLineNum = 244;BA.debugLine="bullet.Put(\"x\", x + offsetX)";
_bullet.Put((Object)("x"),(Object)(_x+_offsetx));
 //BA.debugLineNum = 245;BA.debugLine="bullet.Put(\"y\", y)";
_bullet.Put((Object)("y"),(Object)(_y));
 //BA.debugLineNum = 246;BA.debugLine="If vx <> 0 Then bullet.Put(\"vx\", vx)";
if (_vx!=0) { 
_bullet.Put((Object)("vx"),(Object)(_vx));};
 //BA.debugLineNum = 247;BA.debugLine="enemyBullets.Add(bullet)";
_enemybullets.Add((Object)(_bullet.getObject()));
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public String  _firebullet() throws Exception{
int _maxbullets = 0;
anywheresoftware.b4a.objects.collections.Map _b = null;
 //BA.debugLineNum = 495;BA.debugLine="Public Sub FireBullet";
 //BA.debugLineNum = 496;BA.debugLine="Dim maxBullets As Int = 3";
_maxbullets = (int) (3);
 //BA.debugLineNum = 497;BA.debugLine="If activePowerup = \"RAPID\" Then maxBullets = 6";
if ((_activepowerup).equals("RAPID")) { 
_maxbullets = (int) (6);};
 //BA.debugLineNum = 498;BA.debugLine="If playerBullets.Size < maxBullets Then";
if (_playerbullets.getSize()<_maxbullets) { 
 //BA.debugLineNum = 499;BA.debugLine="Dim b As Map";
_b = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 500;BA.debugLine="b.Initialize";
_b.Initialize();
 //BA.debugLineNum = 501;BA.debugLine="b.Put(\"x\", playerX + playerWidth/2)";
_b.Put((Object)("x"),(Object)(_playerx+_playerwidth/(double)2));
 //BA.debugLineNum = 502;BA.debugLine="b.Put(\"y\", playerY)";
_b.Put((Object)("y"),(Object)(_playery));
 //BA.debugLineNum = 503;BA.debugLine="playerBullets.Add(b)";
_playerbullets.Add((Object)(_b.getObject()));
 };
 //BA.debugLineNum = 505;BA.debugLine="End Sub";
return "";
}
public int  _gethighscore() throws Exception{
 //BA.debugLineNum = 613;BA.debugLine="Public Sub GetHighScore As Int";
 //BA.debugLineNum = 614;BA.debugLine="Return highScore";
if (true) return _highscore;
 //BA.debugLineNum = 615;BA.debugLine="End Sub";
return 0;
}
public int  _getlives() throws Exception{
 //BA.debugLineNum = 609;BA.debugLine="Public Sub GetLives As Int";
 //BA.debugLineNum = 610;BA.debugLine="Return lives";
if (true) return _lives;
 //BA.debugLineNum = 611;BA.debugLine="End Sub";
return 0;
}
public int  _getscore() throws Exception{
 //BA.debugLineNum = 601;BA.debugLine="Public Sub GetScore As Int";
 //BA.debugLineNum = 602;BA.debugLine="Return currentScore";
if (true) return _currentscore;
 //BA.debugLineNum = 603;BA.debugLine="End Sub";
return 0;
}
public int  _getwave() throws Exception{
 //BA.debugLineNum = 605;BA.debugLine="Public Sub GetWave As Int";
 //BA.debugLineNum = 606;BA.debugLine="Return currentWave";
if (true) return _currentwave;
 //BA.debugLineNum = 607;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.PanelWrapper _panel) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 74;BA.debugLine="Public Sub Initialize(panel As Panel)";
 //BA.debugLineNum = 75;BA.debugLine="gamePanel = panel";
_gamepanel = _panel;
 //BA.debugLineNum = 76;BA.debugLine="gameCanvas.Initialize(gamePanel)";
_gamecanvas.Initialize((android.view.View)(_gamepanel.getObject()));
 //BA.debugLineNum = 77;BA.debugLine="soundManager.Initialize";
_soundmanager._initialize /*String*/ (ba);
 //BA.debugLineNum = 78;BA.debugLine="soundManager.LoadAudio";
_soundmanager._loadaudio /*String*/ ();
 //BA.debugLineNum = 80;BA.debugLine="playerBullets.Initialize";
_playerbullets.Initialize();
 //BA.debugLineNum = 81;BA.debugLine="enemyBullets.Initialize";
_enemybullets.Initialize();
 //BA.debugLineNum = 82;BA.debugLine="invaders.Initialize";
_invaders.Initialize();
 //BA.debugLineNum = 83;BA.debugLine="shields.Initialize";
_shields.Initialize();
 //BA.debugLineNum = 84;BA.debugLine="powerups.Initialize";
_powerups.Initialize();
 //BA.debugLineNum = 85;BA.debugLine="particles.Initialize";
_particles.Initialize();
 //BA.debugLineNum = 87;BA.debugLine="highScore = 0";
_highscore = (int) (0);
 //BA.debugLineNum = 88;BA.debugLine="ResetGameState";
_resetgamestate();
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public String  _initializewave() throws Exception{
int _rows = 0;
int _cols = 0;
int _row = 0;
int _col = 0;
anywheresoftware.b4a.objects.collections.Map _inv = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _shield = null;
 //BA.debugLineNum = 118;BA.debugLine="Private Sub InitializeWave";
 //BA.debugLineNum = 119;BA.debugLine="invaders.Clear";
_invaders.Clear();
 //BA.debugLineNum = 120;BA.debugLine="shields.Clear";
_shields.Clear();
 //BA.debugLineNum = 123;BA.debugLine="isBossWave = (currentWave Mod 5 = 0)";
_isbosswave = (_currentwave%5==0);
 //BA.debugLineNum = 125;BA.debugLine="If isBossWave Then";
if (_isbosswave) { 
 //BA.debugLineNum = 126;BA.debugLine="bossActive = True";
_bossactive = __c.True;
 //BA.debugLineNum = 127;BA.debugLine="bossX = gamePanel.Width / 2 - bossWidth / 2";
_bossx = (float) (_gamepanel.getWidth()/(double)2-_bosswidth/(double)2);
 //BA.debugLineNum = 128;BA.debugLine="bossY = 80";
_bossy = (int) (80);
 //BA.debugLineNum = 129;BA.debugLine="bossHealth = bossMaxHealth + (currentWave * 3)";
_bosshealth = (int) (_bossmaxhealth+(_currentwave*3));
 //BA.debugLineNum = 130;BA.debugLine="bossMaxHealth = bossHealth";
_bossmaxhealth = _bosshealth;
 //BA.debugLineNum = 131;BA.debugLine="bossPattern = 0";
_bosspattern = (int) (0);
 }else {
 //BA.debugLineNum = 134;BA.debugLine="Dim rows As Int = 5";
_rows = (int) (5);
 //BA.debugLineNum = 135;BA.debugLine="Dim cols As Int = 11";
_cols = (int) (11);
 //BA.debugLineNum = 136;BA.debugLine="For row = 0 To rows - 1";
{
final int step14 = 1;
final int limit14 = (int) (_rows-1);
_row = (int) (0) ;
for (;_row <= limit14 ;_row = _row + step14 ) {
 //BA.debugLineNum = 137;BA.debugLine="For col = 0 To cols - 1";
{
final int step15 = 1;
final int limit15 = (int) (_cols-1);
_col = (int) (0) ;
for (;_col <= limit15 ;_col = _col + step15 ) {
 //BA.debugLineNum = 138;BA.debugLine="Dim inv As Map";
_inv = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 139;BA.debugLine="inv.Initialize";
_inv.Initialize();
 //BA.debugLineNum = 140;BA.debugLine="inv.Put(\"x\", 50 + (col * 40))";
_inv.Put((Object)("x"),(Object)(50+(_col*40)));
 //BA.debugLineNum = 141;BA.debugLine="inv.Put(\"y\", 80 + (row * 35))";
_inv.Put((Object)("y"),(Object)(80+(_row*35)));
 //BA.debugLineNum = 142;BA.debugLine="inv.Put(\"alive\", True)";
_inv.Put((Object)("alive"),(Object)(__c.True));
 //BA.debugLineNum = 143;BA.debugLine="invaders.Add(inv)";
_invaders.Add((Object)(_inv.getObject()));
 }
};
 }
};
 };
 //BA.debugLineNum = 149;BA.debugLine="For i = 1 To 4";
{
final int step25 = 1;
final int limit25 = (int) (4);
_i = (int) (1) ;
for (;_i <= limit25 ;_i = _i + step25 ) {
 //BA.debugLineNum = 150;BA.debugLine="Dim shield As Map";
_shield = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 151;BA.debugLine="shield.Initialize";
_shield.Initialize();
 //BA.debugLineNum = 152;BA.debugLine="shield.Put(\"x\", i * (gamePanel.Width / 5) - 30)";
_shield.Put((Object)("x"),(Object)(_i*(_gamepanel.getWidth()/(double)5)-30));
 //BA.debugLineNum = 153;BA.debugLine="shield.Put(\"y\", gamePanel.Height - 150)";
_shield.Put((Object)("y"),(Object)(_gamepanel.getHeight()-150));
 //BA.debugLineNum = 154;BA.debugLine="shield.Put(\"health\", 10)";
_shield.Put((Object)("health"),(Object)(10));
 //BA.debugLineNum = 155;BA.debugLine="shields.Add(shield)";
_shields.Add((Object)(_shield.getObject()));
 }
};
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return "";
}
public int  _loadhighscore() throws Exception{
 //BA.debugLineNum = 597;BA.debugLine="Private Sub LoadHighScore As Int";
 //BA.debugLineNum = 598;BA.debugLine="Return 0";
if (true) return (int) (0);
 //BA.debugLineNum = 599;BA.debugLine="End Sub";
return 0;
}
public String  _moveplayer(int _direction) throws Exception{
 //BA.debugLineNum = 486;BA.debugLine="Public Sub MovePlayer(direction As Int)";
 //BA.debugLineNum = 487;BA.debugLine="playerTargetX = playerTargetX + (direction * 20)";
_playertargetx = (float) (_playertargetx+(_direction*20));
 //BA.debugLineNum = 488;BA.debugLine="playerTargetX = Max(0, Min(gamePanel.Width - play";
_playertargetx = (float) (__c.Max(0,__c.Min(_gamepanel.getWidth()-_playerwidth,_playertargetx)));
 //BA.debugLineNum = 489;BA.debugLine="End Sub";
return "";
}
public String  _nextwave() throws Exception{
 //BA.debugLineNum = 475;BA.debugLine="Private Sub NextWave";
 //BA.debugLineNum = 476;BA.debugLine="currentWave = currentWave + 1";
_currentwave = (int) (_currentwave+1);
 //BA.debugLineNum = 477;BA.debugLine="If invaderMoveInterval > 5 Then invaderMoveInterv";
if (_invadermoveinterval>5) { 
_invadermoveinterval = (int) (_invadermoveinterval-1);};
 //BA.debugLineNum = 478;BA.debugLine="InitializeWave";
_initializewave();
 //BA.debugLineNum = 479;BA.debugLine="playerBullets.Clear";
_playerbullets.Clear();
 //BA.debugLineNum = 480;BA.debugLine="enemyBullets.Clear";
_enemybullets.Clear();
 //BA.debugLineNum = 481;BA.debugLine="End Sub";
return "";
}
public String  _pause() throws Exception{
 //BA.debugLineNum = 159;BA.debugLine="Public Sub Pause";
 //BA.debugLineNum = 160;BA.debugLine="IsPausedState = True";
_ispausedstate = __c.True;
 //BA.debugLineNum = 161;BA.debugLine="soundManager.PauseMusic";
_soundmanager._pausemusic /*String*/ ();
 //BA.debugLineNum = 162;BA.debugLine="End Sub";
return "";
}
public String  _resetgamestate() throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Private Sub ResetGameState";
 //BA.debugLineNum = 99;BA.debugLine="currentScore = 0";
_currentscore = (int) (0);
 //BA.debugLineNum = 100;BA.debugLine="currentWave = 1";
_currentwave = (int) (1);
 //BA.debugLineNum = 101;BA.debugLine="lives = INITIAL_LIVES";
_lives = _initial_lives;
 //BA.debugLineNum = 102;BA.debugLine="frameCounter = 0";
_framecounter = (int) (0);
 //BA.debugLineNum = 103;BA.debugLine="comboCount = 0";
_combocount = (int) (0);
 //BA.debugLineNum = 104;BA.debugLine="comboTimer = 0";
_combotimer = (int) (0);
 //BA.debugLineNum = 105;BA.debugLine="comboMultiplier = 1.0";
_combomultiplier = (float) (1.0);
 //BA.debugLineNum = 107;BA.debugLine="playerX = gamePanel.Width / 2 - playerWidth / 2";
_playerx = (float) (_gamepanel.getWidth()/(double)2-_playerwidth/(double)2);
 //BA.debugLineNum = 108;BA.debugLine="playerTargetX = playerX";
_playertargetx = _playerx;
 //BA.debugLineNum = 109;BA.debugLine="playerY = gamePanel.Height - 60";
_playery = (int) (_gamepanel.getHeight()-60);
 //BA.debugLineNum = 111;BA.debugLine="InitializeWave";
_initializewave();
 //BA.debugLineNum = 112;BA.debugLine="playerBullets.Clear";
_playerbullets.Clear();
 //BA.debugLineNum = 113;BA.debugLine="enemyBullets.Clear";
_enemybullets.Clear();
 //BA.debugLineNum = 114;BA.debugLine="powerups.Clear";
_powerups.Clear();
 //BA.debugLineNum = 115;BA.debugLine="particles.Clear";
_particles.Clear();
 //BA.debugLineNum = 116;BA.debugLine="End Sub";
return "";
}
public String  _resume() throws Exception{
 //BA.debugLineNum = 164;BA.debugLine="Public Sub Resume";
 //BA.debugLineNum = 165;BA.debugLine="IsPausedState = False";
_ispausedstate = __c.False;
 //BA.debugLineNum = 166;BA.debugLine="soundManager.ResumeMusic";
_soundmanager._resumemusic /*String*/ ();
 //BA.debugLineNum = 167;BA.debugLine="End Sub";
return "";
}
public String  _setplayermovement(int _movement) throws Exception{
 //BA.debugLineNum = 491;BA.debugLine="Public Sub SetPlayerMovement(movement As Int)";
 //BA.debugLineNum = 493;BA.debugLine="End Sub";
return "";
}
public String  _spawnpowerup(float _x,float _y) throws Exception{
anywheresoftware.b4a.objects.collections.Map _p = null;
 //BA.debugLineNum = 451;BA.debugLine="Private Sub SpawnPowerup(x As Float, y As Float)";
 //BA.debugLineNum = 452;BA.debugLine="Dim p As Map";
_p = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 453;BA.debugLine="p.Initialize";
_p.Initialize();
 //BA.debugLineNum = 454;BA.debugLine="p.Put(\"x\", x)";
_p.Put((Object)("x"),(Object)(_x));
 //BA.debugLineNum = 455;BA.debugLine="p.Put(\"y\", y)";
_p.Put((Object)("y"),(Object)(_y));
 //BA.debugLineNum = 456;BA.debugLine="p.Put(\"type\", Array(\"RAPID\", \"SHIELD\")(Rnd(0, 2))";
_p.Put((Object)("type"),new Object[]{(Object)("RAPID"),(Object)("SHIELD")}[__c.Rnd((int) (0),(int) (2))]);
 //BA.debugLineNum = 457;BA.debugLine="powerups.Add(p)";
_powerups.Add((Object)(_p.getObject()));
 //BA.debugLineNum = 458;BA.debugLine="End Sub";
return "";
}
public String  _startnewgame() throws Exception{
 //BA.debugLineNum = 91;BA.debugLine="Public Sub StartNewGame";
 //BA.debugLineNum = 92;BA.debugLine="ResetGameState";
_resetgamestate();
 //BA.debugLineNum = 93;BA.debugLine="IsActive = True";
_isactive = __c.True;
 //BA.debugLineNum = 94;BA.debugLine="IsPausedState = False";
_ispausedstate = __c.False;
 //BA.debugLineNum = 95;BA.debugLine="soundManager.StartMusic";
_soundmanager._startmusic /*String*/ ();
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public String  _triggergameover() throws Exception{
 //BA.debugLineNum = 590;BA.debugLine="Private Sub TriggerGameOver";
 //BA.debugLineNum = 591;BA.debugLine="IsActive = False";
_isactive = __c.False;
 //BA.debugLineNum = 592;BA.debugLine="soundManager.StopMusic";
_soundmanager._stopmusic /*String*/ ();
 //BA.debugLineNum = 593;BA.debugLine="If currentScore > highScore Then highScore = curr";
if (_currentscore>_highscore) { 
_highscore = _currentscore;};
 //BA.debugLineNum = 594;BA.debugLine="CallSubDelayed2(SpaceInvaders, \"HandleGameOver\",";
__c.CallSubDelayed2(ba,(Object)(_spaceinvaders.getObject()),"HandleGameOver",(Object)(new Object[]{(Object)(_currentscore),(Object)(_currentwave)}));
 //BA.debugLineNum = 595;BA.debugLine="End Sub";
return "";
}
public String  _update() throws Exception{
 //BA.debugLineNum = 172;BA.debugLine="Public Sub Update";
 //BA.debugLineNum = 173;BA.debugLine="If Not(IsActive) Or IsPausedState Then Return";
if (__c.Not(_isactive) || _ispausedstate) { 
if (true) return "";};
 //BA.debugLineNum = 175;BA.debugLine="frameCounter = frameCounter + 1";
_framecounter = (int) (_framecounter+1);
 //BA.debugLineNum = 178;BA.debugLine="If playerX < playerTargetX Then";
if (_playerx<_playertargetx) { 
 //BA.debugLineNum = 179;BA.debugLine="playerX = playerX + (playerTargetX - playerX) *";
_playerx = (float) (_playerx+(_playertargetx-_playerx)*_player_smoothness);
 //BA.debugLineNum = 180;BA.debugLine="If Abs(playerTargetX - playerX) < 1 Then playerX";
if (__c.Abs(_playertargetx-_playerx)<1) { 
_playerx = _playertargetx;};
 }else if(_playerx>_playertargetx) { 
 //BA.debugLineNum = 182;BA.debugLine="playerX = playerX - (playerX - playerTargetX) *";
_playerx = (float) (_playerx-(_playerx-_playertargetx)*_player_smoothness);
 //BA.debugLineNum = 183;BA.debugLine="If Abs(playerX - playerTargetX) < 1 Then playerX";
if (__c.Abs(_playerx-_playertargetx)<1) { 
_playerx = _playertargetx;};
 };
 //BA.debugLineNum = 187;BA.debugLine="If comboTimer > 0 Then";
if (_combotimer>0) { 
 //BA.debugLineNum = 188;BA.debugLine="comboTimer = comboTimer - 1";
_combotimer = (int) (_combotimer-1);
 //BA.debugLineNum = 189;BA.debugLine="If comboTimer = 0 Then";
if (_combotimer==0) { 
 //BA.debugLineNum = 190;BA.debugLine="comboCount = 0";
_combocount = (int) (0);
 //BA.debugLineNum = 191;BA.debugLine="comboMultiplier = 1.0";
_combomultiplier = (float) (1.0);
 };
 };
 //BA.debugLineNum = 196;BA.debugLine="If powerupTimer > 0 Then";
if (_poweruptimer>0) { 
 //BA.debugLineNum = 197;BA.debugLine="powerupTimer = powerupTimer - 1";
_poweruptimer = (int) (_poweruptimer-1);
 //BA.debugLineNum = 198;BA.debugLine="If powerupTimer = 0 Then";
if (_poweruptimer==0) { 
 //BA.debugLineNum = 199;BA.debugLine="activePowerup = \"NONE\"";
_activepowerup = "NONE";
 };
 };
 //BA.debugLineNum = 204;BA.debugLine="If isBossWave And bossActive Then";
if (_isbosswave && _bossactive) { 
 //BA.debugLineNum = 205;BA.debugLine="UpdateBoss";
_updateboss();
 }else {
 //BA.debugLineNum = 207;BA.debugLine="If frameCounter Mod invaderMoveInterval = 0 Then";
if (_framecounter%_invadermoveinterval==0) { 
 //BA.debugLineNum = 208;BA.debugLine="UpdateInvaders";
_updateinvaders();
 };
 };
 //BA.debugLineNum = 212;BA.debugLine="UpdateBullets";
_updatebullets();
 //BA.debugLineNum = 213;BA.debugLine="UpdatePowerups";
_updatepowerups();
 //BA.debugLineNum = 214;BA.debugLine="UpdateParticles";
_updateparticles();
 //BA.debugLineNum = 215;BA.debugLine="CheckCollisions";
_checkcollisions();
 //BA.debugLineNum = 216;BA.debugLine="CheckWinCondition";
_checkwincondition();
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public String  _updateboss() throws Exception{
int _i = 0;
 //BA.debugLineNum = 219;BA.debugLine="Private Sub UpdateBoss";
 //BA.debugLineNum = 221;BA.debugLine="bossX = bossX + 2 * Sin(frameCounter * 0.05)";
_bossx = (float) (_bossx+2*__c.Sin(_framecounter*0.05));
 //BA.debugLineNum = 224;BA.debugLine="If frameCounter Mod 50 = 0 Then";
if (_framecounter%50==0) { 
 //BA.debugLineNum = 225;BA.debugLine="bossPattern = (bossPattern + 1) Mod 3";
_bosspattern = (int) ((_bosspattern+1)%3);
 //BA.debugLineNum = 226;BA.debugLine="Select bossPattern";
switch (_bosspattern) {
case 0: {
 //BA.debugLineNum = 228;BA.debugLine="FireBossBullet(bossX + bossWidth/2, bossY + bo";
_firebossbullet((float) (_bossx+_bosswidth/(double)2),(int) (_bossy+_bossheight),(float) (0),(int) (0));
 break; }
case 1: {
 //BA.debugLineNum = 230;BA.debugLine="For i = -1 To 1";
{
final int step8 = 1;
final int limit8 = (int) (1);
_i = (int) (-1) ;
for (;_i <= limit8 ;_i = _i + step8 ) {
 //BA.debugLineNum = 231;BA.debugLine="FireBossBullet(bossX + bossWidth/2 + (i*20),";
_firebossbullet((float) (_bossx+_bosswidth/(double)2+(_i*20)),(int) (_bossy+_bossheight),(float) (_i*2),(int) (0));
 }
};
 break; }
case 2: {
 //BA.debugLineNum = 234;BA.debugLine="For i = -2 To 2";
{
final int step12 = 1;
final int limit12 = (int) (2);
_i = (int) (-2) ;
for (;_i <= limit12 ;_i = _i + step12 ) {
 //BA.debugLineNum = 235;BA.debugLine="FireBossBullet(bossX + bossWidth/2, bossY + b";
_firebossbullet((float) (_bossx+_bosswidth/(double)2),(int) (_bossy+_bossheight),(float) (_i*3),_i);
 }
};
 break; }
}
;
 };
 //BA.debugLineNum = 239;BA.debugLine="End Sub";
return "";
}
public String  _updatebullets() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _b = null;
int _y = 0;
float _x = 0f;
 //BA.debugLineNum = 301;BA.debugLine="Private Sub UpdateBullets";
 //BA.debugLineNum = 302;BA.debugLine="For i = playerBullets.Size - 1 To 0 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (0);
_i = (int) (_playerbullets.getSize()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 303;BA.debugLine="Dim b As Map = playerBullets.Get(i)";
_b = new anywheresoftware.b4a.objects.collections.Map();
_b = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_playerbullets.Get(_i)));
 //BA.debugLineNum = 304;BA.debugLine="Dim y As Int = b.Get(\"y\") - 15";
_y = (int) ((double)(BA.ObjectToNumber(_b.Get((Object)("y"))))-15);
 //BA.debugLineNum = 305;BA.debugLine="If y < 0 Then";
if (_y<0) { 
 //BA.debugLineNum = 306;BA.debugLine="playerBullets.RemoveAt(i)";
_playerbullets.RemoveAt(_i);
 }else {
 //BA.debugLineNum = 308;BA.debugLine="b.Put(\"y\", y)";
_b.Put((Object)("y"),(Object)(_y));
 };
 }
};
 //BA.debugLineNum = 312;BA.debugLine="For i = enemyBullets.Size - 1 To 0 Step -1";
{
final int step10 = -1;
final int limit10 = (int) (0);
_i = (int) (_enemybullets.getSize()-1) ;
for (;_i >= limit10 ;_i = _i + step10 ) {
 //BA.debugLineNum = 313;BA.debugLine="Dim b As Map = enemyBullets.Get(i)";
_b = new anywheresoftware.b4a.objects.collections.Map();
_b = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_enemybullets.Get(_i)));
 //BA.debugLineNum = 314;BA.debugLine="Dim y As Int = b.Get(\"y\") + 7";
_y = (int) ((double)(BA.ObjectToNumber(_b.Get((Object)("y"))))+7);
 //BA.debugLineNum = 315;BA.debugLine="Dim x As Float = b.Get(\"x\")";
_x = (float)(BA.ObjectToNumber(_b.Get((Object)("x"))));
 //BA.debugLineNum = 316;BA.debugLine="If b.ContainsKey(\"vx\") Then";
if (_b.ContainsKey((Object)("vx"))) { 
 //BA.debugLineNum = 317;BA.debugLine="x = x + b.Get(\"vx\")";
_x = (float) (_x+(double)(BA.ObjectToNumber(_b.Get((Object)("vx")))));
 //BA.debugLineNum = 318;BA.debugLine="b.Put(\"x\", x)";
_b.Put((Object)("x"),(Object)(_x));
 };
 //BA.debugLineNum = 320;BA.debugLine="If y > gamePanel.Height Then";
if (_y>_gamepanel.getHeight()) { 
 //BA.debugLineNum = 321;BA.debugLine="enemyBullets.RemoveAt(i)";
_enemybullets.RemoveAt(_i);
 }else {
 //BA.debugLineNum = 323;BA.debugLine="b.Put(\"y\", y)";
_b.Put((Object)("y"),(Object)(_y));
 };
 }
};
 //BA.debugLineNum = 326;BA.debugLine="End Sub";
return "";
}
public String  _updateinvaders() throws Exception{
int _leftmost = 0;
int _rightmost = 0;
boolean _shoulddrop = false;
anywheresoftware.b4a.objects.collections.Map _inv = null;
int _x = 0;
int _y = 0;
anywheresoftware.b4a.objects.collections.List _alive = null;
anywheresoftware.b4a.objects.collections.Map _shooter = null;
anywheresoftware.b4a.objects.collections.Map _bullet = null;
 //BA.debugLineNum = 250;BA.debugLine="Private Sub UpdateInvaders";
 //BA.debugLineNum = 251;BA.debugLine="Dim leftmost As Int = gamePanel.Width";
_leftmost = _gamepanel.getWidth();
 //BA.debugLineNum = 252;BA.debugLine="Dim rightmost As Int = 0";
_rightmost = (int) (0);
 //BA.debugLineNum = 253;BA.debugLine="Dim shouldDrop As Boolean = False";
_shoulddrop = __c.False;
 //BA.debugLineNum = 255;BA.debugLine="For Each inv As Map In invaders";
_inv = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group4 = _invaders;
final int groupLen4 = group4.getSize()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_inv = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group4.Get(index4)));
 //BA.debugLineNum = 256;BA.debugLine="If inv.Get(\"alive\") Then";
if (BA.ObjectToBoolean(_inv.Get((Object)("alive")))) { 
 //BA.debugLineNum = 257;BA.debugLine="Dim x As Int = inv.Get(\"x\")";
_x = (int)(BA.ObjectToNumber(_inv.Get((Object)("x"))));
 //BA.debugLineNum = 258;BA.debugLine="If x < leftmost Then leftmost = x";
if (_x<_leftmost) { 
_leftmost = _x;};
 //BA.debugLineNum = 259;BA.debugLine="If x > rightmost Then rightmost = x";
if (_x>_rightmost) { 
_rightmost = _x;};
 };
 }
};
 //BA.debugLineNum = 263;BA.debugLine="If invaderDirection = 1 And rightmost >= gamePane";
if (_invaderdirection==1 && _rightmost>=_gamepanel.getWidth()-40) { 
 //BA.debugLineNum = 264;BA.debugLine="invaderDirection = -1";
_invaderdirection = (int) (-1);
 //BA.debugLineNum = 265;BA.debugLine="shouldDrop = True";
_shoulddrop = __c.True;
 }else if(_invaderdirection==-1 && _leftmost<=10) { 
 //BA.debugLineNum = 267;BA.debugLine="invaderDirection = 1";
_invaderdirection = (int) (1);
 //BA.debugLineNum = 268;BA.debugLine="shouldDrop = True";
_shoulddrop = __c.True;
 };
 //BA.debugLineNum = 271;BA.debugLine="For Each inv As Map In invaders";
_inv = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group18 = _invaders;
final int groupLen18 = group18.getSize()
;int index18 = 0;
;
for (; index18 < groupLen18;index18++){
_inv = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group18.Get(index18)));
 //BA.debugLineNum = 272;BA.debugLine="If inv.Get(\"alive\") Then";
if (BA.ObjectToBoolean(_inv.Get((Object)("alive")))) { 
 //BA.debugLineNum = 273;BA.debugLine="Dim x As Int = inv.Get(\"x\")";
_x = (int)(BA.ObjectToNumber(_inv.Get((Object)("x"))));
 //BA.debugLineNum = 274;BA.debugLine="Dim y As Int = inv.Get(\"y\")";
_y = (int)(BA.ObjectToNumber(_inv.Get((Object)("y"))));
 //BA.debugLineNum = 275;BA.debugLine="If shouldDrop Then";
if (_shoulddrop) { 
 //BA.debugLineNum = 276;BA.debugLine="inv.Put(\"y\", y + 20)";
_inv.Put((Object)("y"),(Object)(_y+20));
 }else {
 //BA.debugLineNum = 278;BA.debugLine="inv.Put(\"x\", x + invaderDirection * invaderSpe";
_inv.Put((Object)("x"),(Object)(_x+_invaderdirection*_invaderspeed));
 };
 };
 }
};
 //BA.debugLineNum = 284;BA.debugLine="If Rnd(0, 20) = 0 Then";
if (__c.Rnd((int) (0),(int) (20))==0) { 
 //BA.debugLineNum = 285;BA.debugLine="Dim alive As List";
_alive = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 286;BA.debugLine="alive.Initialize";
_alive.Initialize();
 //BA.debugLineNum = 287;BA.debugLine="For Each inv As Map In invaders";
_inv = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group32 = _invaders;
final int groupLen32 = group32.getSize()
;int index32 = 0;
;
for (; index32 < groupLen32;index32++){
_inv = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group32.Get(index32)));
 //BA.debugLineNum = 288;BA.debugLine="If inv.Get(\"alive\") Then alive.Add(inv)";
if (BA.ObjectToBoolean(_inv.Get((Object)("alive")))) { 
_alive.Add((Object)(_inv.getObject()));};
 }
};
 //BA.debugLineNum = 290;BA.debugLine="If alive.Size > 0 Then";
if (_alive.getSize()>0) { 
 //BA.debugLineNum = 291;BA.debugLine="Dim shooter As Map = alive.Get(Rnd(0, alive.Siz";
_shooter = new anywheresoftware.b4a.objects.collections.Map();
_shooter = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_alive.Get(__c.Rnd((int) (0),_alive.getSize()))));
 //BA.debugLineNum = 292;BA.debugLine="Dim bullet As Map";
_bullet = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 293;BA.debugLine="bullet.Initialize";
_bullet.Initialize();
 //BA.debugLineNum = 294;BA.debugLine="bullet.Put(\"x\", shooter.Get(\"x\") + 15)";
_bullet.Put((Object)("x"),(Object)((double)(BA.ObjectToNumber(_shooter.Get((Object)("x"))))+15));
 //BA.debugLineNum = 295;BA.debugLine="bullet.Put(\"y\", shooter.Get(\"y\") + 25)";
_bullet.Put((Object)("y"),(Object)((double)(BA.ObjectToNumber(_shooter.Get((Object)("y"))))+25));
 //BA.debugLineNum = 296;BA.debugLine="enemyBullets.Add(bullet)";
_enemybullets.Add((Object)(_bullet.getObject()));
 };
 };
 //BA.debugLineNum = 299;BA.debugLine="End Sub";
return "";
}
public String  _updateparticles() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _p = null;
int _life = 0;
 //BA.debugLineNum = 340;BA.debugLine="Private Sub UpdateParticles";
 //BA.debugLineNum = 341;BA.debugLine="For i = particles.Size - 1 To 0 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (0);
_i = (int) (_particles.getSize()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 342;BA.debugLine="Dim p As Map = particles.Get(i)";
_p = new anywheresoftware.b4a.objects.collections.Map();
_p = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_particles.Get(_i)));
 //BA.debugLineNum = 343;BA.debugLine="Dim life As Int = p.Get(\"life\") - 1";
_life = (int) ((double)(BA.ObjectToNumber(_p.Get((Object)("life"))))-1);
 //BA.debugLineNum = 344;BA.debugLine="If life <= 0 Then";
if (_life<=0) { 
 //BA.debugLineNum = 345;BA.debugLine="particles.RemoveAt(i)";
_particles.RemoveAt(_i);
 }else {
 //BA.debugLineNum = 347;BA.debugLine="p.Put(\"x\", p.Get(\"x\") + p.Get(\"vx\"))";
_p.Put((Object)("x"),(Object)((double)(BA.ObjectToNumber(_p.Get((Object)("x"))))+(double)(BA.ObjectToNumber(_p.Get((Object)("vx"))))));
 //BA.debugLineNum = 348;BA.debugLine="p.Put(\"y\", p.Get(\"y\") + p.Get(\"vy\"))";
_p.Put((Object)("y"),(Object)((double)(BA.ObjectToNumber(_p.Get((Object)("y"))))+(double)(BA.ObjectToNumber(_p.Get((Object)("vy"))))));
 //BA.debugLineNum = 349;BA.debugLine="p.Put(\"life\", life)";
_p.Put((Object)("life"),(Object)(_life));
 };
 }
};
 //BA.debugLineNum = 352;BA.debugLine="End Sub";
return "";
}
public String  _updatepowerups() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _p = null;
int _y = 0;
 //BA.debugLineNum = 328;BA.debugLine="Private Sub UpdatePowerups";
 //BA.debugLineNum = 329;BA.debugLine="For i = powerups.Size - 1 To 0 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (0);
_i = (int) (_powerups.getSize()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 330;BA.debugLine="Dim p As Map = powerups.Get(i)";
_p = new anywheresoftware.b4a.objects.collections.Map();
_p = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_powerups.Get(_i)));
 //BA.debugLineNum = 331;BA.debugLine="Dim y As Int = p.Get(\"y\") + 3";
_y = (int) ((double)(BA.ObjectToNumber(_p.Get((Object)("y"))))+3);
 //BA.debugLineNum = 332;BA.debugLine="If y > gamePanel.Height Then";
if (_y>_gamepanel.getHeight()) { 
 //BA.debugLineNum = 333;BA.debugLine="powerups.RemoveAt(i)";
_powerups.RemoveAt(_i);
 }else {
 //BA.debugLineNum = 335;BA.debugLine="p.Put(\"y\", y)";
_p.Put((Object)("y"),(Object)(_y));
 };
 }
};
 //BA.debugLineNum = 338;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
