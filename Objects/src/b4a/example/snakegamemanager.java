package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class snakegamemanager extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.snakegamemanager");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.snakegamemanager.class).invoke(this, new Object[] {null});
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
public int _currentlevel = 0;
public int _highscore = 0;
public int _grid_width = 0;
public int _grid_height = 0;
public int _cellsize = 0;
public anywheresoftware.b4a.objects.collections.List _snakebody = null;
public anywheresoftware.b4a.objects.collections.List _snakeanimx = null;
public anywheresoftware.b4a.objects.collections.List _snakeanimy = null;
public float _anim_speed = 0f;
public String _direction = "";
public String _nextdirection = "";
public int _snakelength = 0;
public int _foodx = 0;
public int _foody = 0;
public anywheresoftware.b4a.objects.collections.List _obstacles = null;
public int _obstaclecount = 0;
public anywheresoftware.b4a.objects.collections.List _particles = null;
public int _color_background = 0;
public int _color_snake_head = 0;
public int _color_snake_body = 0;
public int _color_food = 0;
public int _color_grid = 0;
public int _color_obstacle = 0;
public int _food_score = 0;
public int _level_up_threshold = 0;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _checklevelup() throws Exception{
int _foodseaten = 0;
int _newlevel = 0;
 //BA.debugLineNum = 331;BA.debugLine="Private Sub CheckLevelUp";
 //BA.debugLineNum = 332;BA.debugLine="Dim foodsEaten As Int = (snakeLength - 3)";
_foodseaten = (int) ((_snakelength-3));
 //BA.debugLineNum = 333;BA.debugLine="Dim newLevel As Int = (foodsEaten / LEVEL_UP_THRE";
_newlevel = (int) ((_foodseaten/(double)_level_up_threshold)+1);
 //BA.debugLineNum = 335;BA.debugLine="If newLevel > currentLevel Then";
if (_newlevel>_currentlevel) { 
 //BA.debugLineNum = 336;BA.debugLine="currentLevel = newLevel";
_currentlevel = _newlevel;
 //BA.debugLineNum = 337;BA.debugLine="UpdateObstacles  ' Add more obstacles";
_updateobstacles();
 };
 //BA.debugLineNum = 339;BA.debugLine="End Sub";
return "";
}
public boolean  _checkobstaclecollision(int _x,int _y) throws Exception{
anywheresoftware.b4a.objects.collections.Map _obs = null;
 //BA.debugLineNum = 293;BA.debugLine="Private Sub CheckObstacleCollision(x As Int, y As";
 //BA.debugLineNum = 294;BA.debugLine="For Each obs As Map In obstacles";
_obs = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group1 = _obstacles;
final int groupLen1 = group1.getSize()
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_obs = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group1.Get(index1)));
 //BA.debugLineNum = 295;BA.debugLine="If obs.Get(\"x\") = x And obs.Get(\"y\") = y Then";
if ((_obs.Get((Object)("x"))).equals((Object)(_x)) && (_obs.Get((Object)("y"))).equals((Object)(_y))) { 
 //BA.debugLineNum = 296;BA.debugLine="Return True";
if (true) return __c.True;
 };
 }
};
 //BA.debugLineNum = 299;BA.debugLine="Return False";
if (true) return __c.False;
 //BA.debugLineNum = 300;BA.debugLine="End Sub";
return false;
}
public boolean  _checkselfcollision(int _x,int _y) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _segment = null;
 //BA.debugLineNum = 283;BA.debugLine="Private Sub CheckSelfCollision(x As Int, y As Int)";
 //BA.debugLineNum = 284;BA.debugLine="For i = 0 To snakeBody.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (_snakebody.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 285;BA.debugLine="Dim segment As Map = snakeBody.Get(i)";
_segment = new anywheresoftware.b4a.objects.collections.Map();
_segment = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_snakebody.Get(_i)));
 //BA.debugLineNum = 286;BA.debugLine="If segment.Get(\"x\") = x And segment.Get(\"y\") = y";
if ((_segment.Get((Object)("x"))).equals((Object)(_x)) && (_segment.Get((Object)("y"))).equals((Object)(_y))) { 
 //BA.debugLineNum = 287;BA.debugLine="Return True";
if (true) return __c.True;
 };
 }
};
 //BA.debugLineNum = 290;BA.debugLine="Return False";
if (true) return __c.False;
 //BA.debugLineNum = 291;BA.debugLine="End Sub";
return false;
}
public boolean  _checkwallcollision(int _x,int _y) throws Exception{
 //BA.debugLineNum = 279;BA.debugLine="Private Sub CheckWallCollision(x As Int, y As Int)";
 //BA.debugLineNum = 280;BA.debugLine="Return x < 0 Or x >= GRID_WIDTH Or y < 0 Or y >=";
if (true) return _x<0 || _x>=_grid_width || _y<0 || _y>=_grid_height;
 //BA.debugLineNum = 281;BA.debugLine="End Sub";
return false;
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 9;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 10;BA.debugLine="Private gameCanvas As Canvas";
_gamecanvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Private gamePanel As Panel";
_gamepanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Private soundManager As SoundManager";
_soundmanager = new b4a.example.soundmanager();
 //BA.debugLineNum = 14;BA.debugLine="Public IsActive As Boolean";
_isactive = false;
 //BA.debugLineNum = 15;BA.debugLine="Public IsPausedState As Boolean";
_ispausedstate = false;
 //BA.debugLineNum = 16;BA.debugLine="Private currentScore As Int";
_currentscore = 0;
 //BA.debugLineNum = 17;BA.debugLine="Private currentLevel As Int";
_currentlevel = 0;
 //BA.debugLineNum = 18;BA.debugLine="Private highScore As Int";
_highscore = 0;
 //BA.debugLineNum = 21;BA.debugLine="Private Const GRID_WIDTH As Int = 20";
_grid_width = (int) (20);
 //BA.debugLineNum = 22;BA.debugLine="Private Const GRID_HEIGHT As Int = 50";
_grid_height = (int) (50);
 //BA.debugLineNum = 23;BA.debugLine="Private cellSize As Int";
_cellsize = 0;
 //BA.debugLineNum = 26;BA.debugLine="Private snakeBody As List";
_snakebody = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 27;BA.debugLine="Private snakeAnimX As List  ' Smooth interpolated";
_snakeanimx = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 28;BA.debugLine="Private snakeAnimY As List";
_snakeanimy = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 29;BA.debugLine="Private Const ANIM_SPEED As Float = 0.5";
_anim_speed = (float) (0.5);
 //BA.debugLineNum = 31;BA.debugLine="Private direction As String";
_direction = "";
 //BA.debugLineNum = 32;BA.debugLine="Private nextDirection As String";
_nextdirection = "";
 //BA.debugLineNum = 33;BA.debugLine="Private snakeLength As Int";
_snakelength = 0;
 //BA.debugLineNum = 36;BA.debugLine="Private foodX As Int";
_foodx = 0;
 //BA.debugLineNum = 37;BA.debugLine="Private foodY As Int";
_foody = 0;
 //BA.debugLineNum = 40;BA.debugLine="Private obstacles As List";
_obstacles = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 41;BA.debugLine="Private obstacleCount As Int";
_obstaclecount = 0;
 //BA.debugLineNum = 44;BA.debugLine="Private particles As List";
_particles = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 47;BA.debugLine="Private Const COLOR_BACKGROUND As Int = 0xFF28282";
_color_background = ((int)0xff282828);
 //BA.debugLineNum = 48;BA.debugLine="Private Const COLOR_SNAKE_HEAD As Int = 0xFF00ff0";
_color_snake_head = ((int)0xff00ff00);
 //BA.debugLineNum = 49;BA.debugLine="Private Const COLOR_SNAKE_BODY As Int = 0xFF00cc0";
_color_snake_body = ((int)0xff00cc00);
 //BA.debugLineNum = 50;BA.debugLine="Private Const COLOR_FOOD As Int = 0xFFff0000";
_color_food = ((int)0xffff0000);
 //BA.debugLineNum = 51;BA.debugLine="Private Const COLOR_GRID As Int = 0x9BFFFFFF";
_color_grid = ((int)0x9bffffff);
 //BA.debugLineNum = 52;BA.debugLine="Private Const COLOR_OBSTACLE As Int = 0xFF666666";
_color_obstacle = ((int)0xff666666);
 //BA.debugLineNum = 54;BA.debugLine="Private Const FOOD_SCORE As Int = 10";
_food_score = (int) (10);
 //BA.debugLineNum = 55;BA.debugLine="Private Const LEVEL_UP_THRESHOLD As Int = 5";
_level_up_threshold = (int) (5);
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public String  _createfoodparticles(int _x,int _y) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _p = null;
 //BA.debugLineNum = 263;BA.debugLine="Private Sub CreateFoodParticles(x As Int, y As Int";
 //BA.debugLineNum = 264;BA.debugLine="For i = 0 To 8";
{
final int step1 = 1;
final int limit1 = (int) (8);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 265;BA.debugLine="Dim p As Map";
_p = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 266;BA.debugLine="p.Initialize";
_p.Initialize();
 //BA.debugLineNum = 267;BA.debugLine="p.Put(\"x\", x * cellSize + cellSize/2)";
_p.Put((Object)("x"),(Object)(_x*_cellsize+_cellsize/(double)2));
 //BA.debugLineNum = 268;BA.debugLine="p.Put(\"y\", y * cellSize + cellSize/2)";
_p.Put((Object)("y"),(Object)(_y*_cellsize+_cellsize/(double)2));
 //BA.debugLineNum = 269;BA.debugLine="p.Put(\"vx\", (Rnd(-10, 11) / 10.0) * 2)";
_p.Put((Object)("vx"),(Object)((__c.Rnd((int) (-10),(int) (11))/(double)10.0)*2));
 //BA.debugLineNum = 270;BA.debugLine="p.Put(\"vy\", (Rnd(-10, 11) / 10.0) * 2 - 1)";
_p.Put((Object)("vy"),(Object)((__c.Rnd((int) (-10),(int) (11))/(double)10.0)*2-1));
 //BA.debugLineNum = 271;BA.debugLine="p.Put(\"life\", Rnd(15, 25))";
_p.Put((Object)("life"),(Object)(__c.Rnd((int) (15),(int) (25))));
 //BA.debugLineNum = 272;BA.debugLine="particles.Add(p)";
_particles.Add((Object)(_p.getObject()));
 }
};
 //BA.debugLineNum = 274;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.Map  _createpoint(int _x,int _y) throws Exception{
anywheresoftware.b4a.objects.collections.Map _point = null;
 //BA.debugLineNum = 435;BA.debugLine="Private Sub CreatePoint(x As Int, y As Int) As Map";
 //BA.debugLineNum = 436;BA.debugLine="Dim point As Map";
_point = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 437;BA.debugLine="point.Initialize";
_point.Initialize();
 //BA.debugLineNum = 438;BA.debugLine="point.Put(\"x\", x)";
_point.Put((Object)("x"),(Object)(_x));
 //BA.debugLineNum = 439;BA.debugLine="point.Put(\"y\", y)";
_point.Put((Object)("y"),(Object)(_y));
 //BA.debugLineNum = 440;BA.debugLine="Return point";
if (true) return _point;
 //BA.debugLineNum = 441;BA.debugLine="End Sub";
return null;
}
public String  _draw() throws Exception{
int _x = 0;
int _y = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
anywheresoftware.b4a.objects.collections.Map _obs = null;
int _ox = 0;
int _oy = 0;
float _foodpulse = 0f;
int _foodmargin = 0;
int _i = 0;
float _xx = 0f;
float _yy = 0f;
int _color = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _sr = null;
anywheresoftware.b4a.objects.collections.Map _p = null;
int _life = 0;
int _alpha = 0;
int _pcolor = 0;
float _px = 0f;
float _py = 0f;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _pr = null;
 //BA.debugLineNum = 359;BA.debugLine="Public Sub Draw";
 //BA.debugLineNum = 360;BA.debugLine="gameCanvas.DrawColor(COLOR_BACKGROUND)";
_gamecanvas.DrawColor(_color_background);
 //BA.debugLineNum = 363;BA.debugLine="For x = 0 To GRID_WIDTH - 1";
{
final int step2 = 1;
final int limit2 = (int) (_grid_width-1);
_x = (int) (0) ;
for (;_x <= limit2 ;_x = _x + step2 ) {
 //BA.debugLineNum = 364;BA.debugLine="For y = 0 To GRID_HEIGHT - 1";
{
final int step3 = 1;
final int limit3 = (int) (_grid_height-1);
_y = (int) (0) ;
for (;_y <= limit3 ;_y = _y + step3 ) {
 //BA.debugLineNum = 365;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 366;BA.debugLine="rect.Initialize(x * cellSize, y * cellSize, (x";
_rect.Initialize((int) (_x*_cellsize),(int) (_y*_cellsize),(int) ((_x+1)*_cellsize),(int) ((_y+1)*_cellsize));
 //BA.debugLineNum = 367;BA.debugLine="gameCanvas.DrawRect(rect, COLOR_GRID, False, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_rect.getObject()),_color_grid,__c.False,(float) (1));
 }
};
 }
};
 //BA.debugLineNum = 372;BA.debugLine="For Each obs As Map In obstacles";
_obs = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group9 = _obstacles;
final int groupLen9 = group9.getSize()
;int index9 = 0;
;
for (; index9 < groupLen9;index9++){
_obs = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group9.Get(index9)));
 //BA.debugLineNum = 373;BA.debugLine="Dim ox As Int = obs.Get(\"x\")";
_ox = (int)(BA.ObjectToNumber(_obs.Get((Object)("x"))));
 //BA.debugLineNum = 374;BA.debugLine="Dim oy As Int = obs.Get(\"y\")";
_oy = (int)(BA.ObjectToNumber(_obs.Get((Object)("y"))));
 //BA.debugLineNum = 375;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 376;BA.debugLine="rect.Initialize(ox * cellSize + 2, oy * cellSize";
_rect.Initialize((int) (_ox*_cellsize+2),(int) (_oy*_cellsize+2),(int) ((_ox+1)*_cellsize-2),(int) ((_oy+1)*_cellsize-2));
 //BA.debugLineNum = 378;BA.debugLine="gameCanvas.DrawRect(rect, COLOR_OBSTACLE, True,";
_gamecanvas.DrawRect((android.graphics.Rect)(_rect.getObject()),_color_obstacle,__c.True,(float) (1));
 //BA.debugLineNum = 381;BA.debugLine="gameCanvas.DrawLine(ox * cellSize + 4, oy * cell";
_gamecanvas.DrawLine((float) (_ox*_cellsize+4),(float) (_oy*_cellsize+4),(float) ((_ox+1)*_cellsize-4),(float) ((_oy+1)*_cellsize-4),__c.Colors.DarkGray,(float) (2));
 //BA.debugLineNum = 384;BA.debugLine="gameCanvas.DrawLine((ox + 1) * cellSize - 4, oy";
_gamecanvas.DrawLine((float) ((_ox+1)*_cellsize-4),(float) (_oy*_cellsize+4),(float) (_ox*_cellsize+4),(float) ((_oy+1)*_cellsize-4),__c.Colors.DarkGray,(float) (2));
 }
};
 //BA.debugLineNum = 390;BA.debugLine="Dim foodPulse As Float = 1.0 + (Sin(DateTime.Now";
_foodpulse = (float) (1.0+(__c.Sin(__c.DateTime.getNow()/(double)100.0)*0.15));
 //BA.debugLineNum = 391;BA.debugLine="Dim foodMargin As Int = cellSize * (1 - foodPulse";
_foodmargin = (int) (_cellsize*(1-_foodpulse)/(double)2);
 //BA.debugLineNum = 392;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 393;BA.debugLine="rect.Initialize(foodX * cellSize + foodMargin, fo";
_rect.Initialize((int) (_foodx*_cellsize+_foodmargin),(int) (_foody*_cellsize+_foodmargin),(int) ((_foodx+1)*_cellsize-_foodmargin),(int) ((_foody+1)*_cellsize-_foodmargin));
 //BA.debugLineNum = 395;BA.debugLine="gameCanvas.DrawRect(rect, COLOR_FOOD, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_rect.getObject()),_color_food,__c.True,(float) (1));
 //BA.debugLineNum = 398;BA.debugLine="For i = 0 To snakeBody.Size - 1";
{
final int step23 = 1;
final int limit23 = (int) (_snakebody.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit23 ;_i = _i + step23 ) {
 //BA.debugLineNum = 399;BA.debugLine="Dim xx As Float = snakeAnimX.Get(i)";
_xx = (float)(BA.ObjectToNumber(_snakeanimx.Get(_i)));
 //BA.debugLineNum = 400;BA.debugLine="Dim yy As Float = snakeAnimY.Get(i)";
_yy = (float)(BA.ObjectToNumber(_snakeanimy.Get(_i)));
 //BA.debugLineNum = 401;BA.debugLine="Dim color As Int = COLOR_SNAKE_BODY";
_color = _color_snake_body;
 //BA.debugLineNum = 402;BA.debugLine="If i = 0 Then color = COLOR_SNAKE_HEAD";
if (_i==0) { 
_color = _color_snake_head;};
 //BA.debugLineNum = 404;BA.debugLine="Dim sr As Rect";
_sr = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 405;BA.debugLine="sr.Initialize(xx * cellSize + 2, yy * cellSize +";
_sr.Initialize((int) (_xx*_cellsize+2),(int) (_yy*_cellsize+2),(int) ((_xx+1)*_cellsize-2),(int) ((_yy+1)*_cellsize-2));
 //BA.debugLineNum = 407;BA.debugLine="gameCanvas.DrawRect(sr, color, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_sr.getObject()),_color,__c.True,(float) (1));
 }
};
 //BA.debugLineNum = 411;BA.debugLine="For Each p As Map In particles";
_p = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group32 = _particles;
final int groupLen32 = group32.getSize()
;int index32 = 0;
;
for (; index32 < groupLen32;index32++){
_p = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group32.Get(index32)));
 //BA.debugLineNum = 412;BA.debugLine="Dim life As Int = p.Get(\"life\")";
_life = (int)(BA.ObjectToNumber(_p.Get((Object)("life"))));
 //BA.debugLineNum = 413;BA.debugLine="Dim alpha As Int = Min(255, life * 10)";
_alpha = (int) (__c.Min(255,_life*10));
 //BA.debugLineNum = 414;BA.debugLine="Dim pColor As Int = Colors.ARGB(alpha, 255, 0, 0";
_pcolor = __c.Colors.ARGB(_alpha,(int) (255),(int) (0),(int) (0));
 //BA.debugLineNum = 415;BA.debugLine="Dim px As Float = p.Get(\"x\")";
_px = (float)(BA.ObjectToNumber(_p.Get((Object)("x"))));
 //BA.debugLineNum = 416;BA.debugLine="Dim py As Float = p.Get(\"y\")";
_py = (float)(BA.ObjectToNumber(_p.Get((Object)("y"))));
 //BA.debugLineNum = 417;BA.debugLine="Dim pr As Rect";
_pr = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 418;BA.debugLine="pr.Initialize(px - 2, py - 2, px + 2, py + 2)";
_pr.Initialize((int) (_px-2),(int) (_py-2),(int) (_px+2),(int) (_py+2));
 //BA.debugLineNum = 419;BA.debugLine="gameCanvas.DrawRect(pr, pColor, True, 1)";
_gamecanvas.DrawRect((android.graphics.Rect)(_pr.getObject()),_pcolor,__c.True,(float) (1));
 }
};
 //BA.debugLineNum = 422;BA.debugLine="gamePanel.Invalidate";
_gamepanel.Invalidate();
 //BA.debugLineNum = 423;BA.debugLine="End Sub";
return "";
}
public int  _gethighscore() throws Exception{
 //BA.debugLineNum = 454;BA.debugLine="Public Sub GetHighScore As Int";
 //BA.debugLineNum = 455;BA.debugLine="Return highScore";
if (true) return _highscore;
 //BA.debugLineNum = 456;BA.debugLine="End Sub";
return 0;
}
public int  _getlevel() throws Exception{
 //BA.debugLineNum = 450;BA.debugLine="Public Sub GetLevel As Int";
 //BA.debugLineNum = 451;BA.debugLine="Return currentLevel";
if (true) return _currentlevel;
 //BA.debugLineNum = 452;BA.debugLine="End Sub";
return 0;
}
public int  _getscore() throws Exception{
 //BA.debugLineNum = 446;BA.debugLine="Public Sub GetScore As Int";
 //BA.debugLineNum = 447;BA.debugLine="Return currentScore";
if (true) return _currentscore;
 //BA.debugLineNum = 448;BA.debugLine="End Sub";
return 0;
}
public String  _initialize(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.PanelWrapper _panel) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 58;BA.debugLine="Public Sub Initialize(panel As Panel)";
 //BA.debugLineNum = 59;BA.debugLine="gamePanel = panel";
_gamepanel = _panel;
 //BA.debugLineNum = 60;BA.debugLine="gameCanvas.Initialize(gamePanel)";
_gamecanvas.Initialize((android.view.View)(_gamepanel.getObject()));
 //BA.debugLineNum = 61;BA.debugLine="soundManager.Initialize";
_soundmanager._initialize /*String*/ (ba);
 //BA.debugLineNum = 62;BA.debugLine="soundManager.LoadAudio";
_soundmanager._loadaudio /*String*/ ();
 //BA.debugLineNum = 64;BA.debugLine="cellSize = Min(gamePanel.Width / GRID_WIDTH, game";
_cellsize = (int) (__c.Min(_gamepanel.getWidth()/(double)_grid_width,_gamepanel.getHeight()/(double)_grid_height));
 //BA.debugLineNum = 66;BA.debugLine="snakeBody.Initialize";
_snakebody.Initialize();
 //BA.debugLineNum = 67;BA.debugLine="snakeAnimX.Initialize";
_snakeanimx.Initialize();
 //BA.debugLineNum = 68;BA.debugLine="snakeAnimY.Initialize";
_snakeanimy.Initialize();
 //BA.debugLineNum = 69;BA.debugLine="obstacles.Initialize";
_obstacles.Initialize();
 //BA.debugLineNum = 70;BA.debugLine="particles.Initialize";
_particles.Initialize();
 //BA.debugLineNum = 72;BA.debugLine="highScore = 0";
_highscore = (int) (0);
 //BA.debugLineNum = 73;BA.debugLine="ResetGameState";
_resetgamestate();
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return "";
}
public String  _movesnake() throws Exception{
anywheresoftware.b4a.objects.collections.Map _head = null;
int _newx = 0;
int _newy = 0;
boolean _atefood = false;
 //BA.debugLineNum = 198;BA.debugLine="Private Sub MoveSnake";
 //BA.debugLineNum = 199;BA.debugLine="direction = nextDirection";
_direction = _nextdirection;
 //BA.debugLineNum = 201;BA.debugLine="Dim head As Map = snakeBody.Get(0)";
_head = new anywheresoftware.b4a.objects.collections.Map();
_head = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_snakebody.Get((int) (0))));
 //BA.debugLineNum = 202;BA.debugLine="Dim newX As Int = head.Get(\"x\")";
_newx = (int)(BA.ObjectToNumber(_head.Get((Object)("x"))));
 //BA.debugLineNum = 203;BA.debugLine="Dim newY As Int = head.Get(\"y\")";
_newy = (int)(BA.ObjectToNumber(_head.Get((Object)("y"))));
 //BA.debugLineNum = 205;BA.debugLine="Select direction";
switch (BA.switchObjectToInt(_direction,"UP","DOWN","LEFT","RIGHT")) {
case 0: {
 //BA.debugLineNum = 207;BA.debugLine="newY = newY - 1";
_newy = (int) (_newy-1);
 break; }
case 1: {
 //BA.debugLineNum = 209;BA.debugLine="newY = newY + 1";
_newy = (int) (_newy+1);
 break; }
case 2: {
 //BA.debugLineNum = 211;BA.debugLine="newX = newX - 1";
_newx = (int) (_newx-1);
 break; }
case 3: {
 //BA.debugLineNum = 213;BA.debugLine="newX = newX + 1";
_newx = (int) (_newx+1);
 break; }
}
;
 //BA.debugLineNum = 217;BA.debugLine="If CheckWallCollision(newX, newY) Or _ 	   CheckS";
if (_checkwallcollision(_newx,_newy) || _checkselfcollision(_newx,_newy) || _checkobstaclecollision(_newx,_newy)) { 
 //BA.debugLineNum = 220;BA.debugLine="TriggerGameOver";
_triggergameover();
 //BA.debugLineNum = 221;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 225;BA.debugLine="Dim ateFood As Boolean = (newX = foodX And newY =";
_atefood = (_newx==_foodx && _newy==_foody);
 //BA.debugLineNum = 228;BA.debugLine="snakeBody.InsertAt(0, CreatePoint(newX, newY))";
_snakebody.InsertAt((int) (0),(Object)(_createpoint(_newx,_newy).getObject()));
 //BA.debugLineNum = 229;BA.debugLine="snakeAnimX.InsertAt(0, newX)";
_snakeanimx.InsertAt((int) (0),(Object)(_newx));
 //BA.debugLineNum = 230;BA.debugLine="snakeAnimY.InsertAt(0, newY)";
_snakeanimy.InsertAt((int) (0),(Object)(_newy));
 //BA.debugLineNum = 232;BA.debugLine="If ateFood Then";
if (_atefood) { 
 //BA.debugLineNum = 233;BA.debugLine="currentScore = currentScore + (FOOD_SCORE * curr";
_currentscore = (int) (_currentscore+(_food_score*_currentlevel));
 //BA.debugLineNum = 234;BA.debugLine="snakeLength = snakeLength + 1";
_snakelength = (int) (_snakelength+1);
 //BA.debugLineNum = 235;BA.debugLine="soundManager.PlayLineClearSound";
_soundmanager._playlineclearsound /*String*/ ();
 //BA.debugLineNum = 236;BA.debugLine="CreateFoodParticles(foodX, foodY)";
_createfoodparticles(_foodx,_foody);
 //BA.debugLineNum = 237;BA.debugLine="SpawnFood";
_spawnfood();
 //BA.debugLineNum = 238;BA.debugLine="CheckLevelUp";
_checklevelup();
 }else {
 //BA.debugLineNum = 241;BA.debugLine="snakeBody.RemoveAt(snakeBody.Size - 1)";
_snakebody.RemoveAt((int) (_snakebody.getSize()-1));
 //BA.debugLineNum = 242;BA.debugLine="snakeAnimX.RemoveAt(snakeAnimX.Size - 1)";
_snakeanimx.RemoveAt((int) (_snakeanimx.getSize()-1));
 //BA.debugLineNum = 243;BA.debugLine="snakeAnimY.RemoveAt(snakeAnimY.Size - 1)";
_snakeanimy.RemoveAt((int) (_snakeanimy.getSize()-1));
 };
 //BA.debugLineNum = 245;BA.debugLine="End Sub";
return "";
}
public String  _pause() throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Public Sub Pause";
 //BA.debugLineNum = 149;BA.debugLine="IsPausedState = True";
_ispausedstate = __c.True;
 //BA.debugLineNum = 150;BA.debugLine="soundManager.PauseMusic";
_soundmanager._pausemusic /*String*/ ();
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public String  _resetgamestate() throws Exception{
int _startx = 0;
int _starty = 0;
int _i = 0;
 //BA.debugLineNum = 83;BA.debugLine="Private Sub ResetGameState";
 //BA.debugLineNum = 84;BA.debugLine="snakeBody.Clear";
_snakebody.Clear();
 //BA.debugLineNum = 85;BA.debugLine="snakeAnimX.Clear";
_snakeanimx.Clear();
 //BA.debugLineNum = 86;BA.debugLine="snakeAnimY.Clear";
_snakeanimy.Clear();
 //BA.debugLineNum = 87;BA.debugLine="obstacles.Clear";
_obstacles.Clear();
 //BA.debugLineNum = 88;BA.debugLine="particles.Clear";
_particles.Clear();
 //BA.debugLineNum = 90;BA.debugLine="currentScore = 0";
_currentscore = (int) (0);
 //BA.debugLineNum = 91;BA.debugLine="currentLevel = 1";
_currentlevel = (int) (1);
 //BA.debugLineNum = 92;BA.debugLine="snakeLength = 3";
_snakelength = (int) (3);
 //BA.debugLineNum = 93;BA.debugLine="direction = \"RIGHT\"";
_direction = "RIGHT";
 //BA.debugLineNum = 94;BA.debugLine="nextDirection = \"RIGHT\"";
_nextdirection = "RIGHT";
 //BA.debugLineNum = 97;BA.debugLine="Dim startX As Int = GRID_WIDTH / 2";
_startx = (int) (_grid_width/(double)2);
 //BA.debugLineNum = 98;BA.debugLine="Dim startY As Int = GRID_HEIGHT / 2";
_starty = (int) (_grid_height/(double)2);
 //BA.debugLineNum = 99;BA.debugLine="For i = 0 To snakeLength - 1";
{
final int step13 = 1;
final int limit13 = (int) (_snakelength-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 100;BA.debugLine="snakeBody.Add(CreatePoint(startX - i, startY))";
_snakebody.Add((Object)(_createpoint((int) (_startx-_i),_starty).getObject()));
 //BA.debugLineNum = 101;BA.debugLine="snakeAnimX.Add(startX - i)";
_snakeanimx.Add((Object)(_startx-_i));
 //BA.debugLineNum = 102;BA.debugLine="snakeAnimY.Add(startY)";
_snakeanimy.Add((Object)(_starty));
 }
};
 //BA.debugLineNum = 105;BA.debugLine="SpawnFood";
_spawnfood();
 //BA.debugLineNum = 106;BA.debugLine="UpdateObstacles";
_updateobstacles();
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public String  _resume() throws Exception{
 //BA.debugLineNum = 153;BA.debugLine="Public Sub Resume";
 //BA.debugLineNum = 154;BA.debugLine="IsPausedState = False";
_ispausedstate = __c.False;
 //BA.debugLineNum = 155;BA.debugLine="soundManager.ResumeMusic";
_soundmanager._resumemusic /*String*/ ();
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public String  _setdirection(String _newdir) throws Exception{
 //BA.debugLineNum = 344;BA.debugLine="Public Sub SetDirection(newDir As String)";
 //BA.debugLineNum = 345;BA.debugLine="If newDir = \"UP\" And direction <> \"DOWN\" Then";
if ((_newdir).equals("UP") && (_direction).equals("DOWN") == false) { 
 //BA.debugLineNum = 346;BA.debugLine="nextDirection = \"UP\"";
_nextdirection = "UP";
 }else if((_newdir).equals("DOWN") && (_direction).equals("UP") == false) { 
 //BA.debugLineNum = 348;BA.debugLine="nextDirection = \"DOWN\"";
_nextdirection = "DOWN";
 }else if((_newdir).equals("LEFT") && (_direction).equals("RIGHT") == false) { 
 //BA.debugLineNum = 350;BA.debugLine="nextDirection = \"LEFT\"";
_nextdirection = "LEFT";
 }else if((_newdir).equals("RIGHT") && (_direction).equals("LEFT") == false) { 
 //BA.debugLineNum = 352;BA.debugLine="nextDirection = \"RIGHT\"";
_nextdirection = "RIGHT";
 };
 //BA.debugLineNum = 354;BA.debugLine="End Sub";
return "";
}
public String  _spawnfood() throws Exception{
boolean _validpos = false;
anywheresoftware.b4a.objects.collections.Map _segment = null;
anywheresoftware.b4a.objects.collections.Map _obs = null;
 //BA.debugLineNum = 305;BA.debugLine="Private Sub SpawnFood";
 //BA.debugLineNum = 306;BA.debugLine="Dim validPos As Boolean = False";
_validpos = __c.False;
 //BA.debugLineNum = 308;BA.debugLine="Do While Not(validPos)";
while (__c.Not(_validpos)) {
 //BA.debugLineNum = 309;BA.debugLine="foodX = Rnd(0, GRID_WIDTH)";
_foodx = __c.Rnd((int) (0),_grid_width);
 //BA.debugLineNum = 310;BA.debugLine="foodY = Rnd(0, GRID_HEIGHT)";
_foody = __c.Rnd((int) (0),_grid_height);
 //BA.debugLineNum = 311;BA.debugLine="validPos = True";
_validpos = __c.True;
 //BA.debugLineNum = 314;BA.debugLine="For Each segment As Map In snakeBody";
_segment = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group6 = _snakebody;
final int groupLen6 = group6.getSize()
;int index6 = 0;
;
for (; index6 < groupLen6;index6++){
_segment = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group6.Get(index6)));
 //BA.debugLineNum = 315;BA.debugLine="If segment.Get(\"x\") = foodX And segment.Get(\"y\"";
if ((_segment.Get((Object)("x"))).equals((Object)(_foodx)) && (_segment.Get((Object)("y"))).equals((Object)(_foody))) { 
 //BA.debugLineNum = 316;BA.debugLine="validPos = False";
_validpos = __c.False;
 //BA.debugLineNum = 317;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 322;BA.debugLine="For Each obs As Map In obstacles";
_obs = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group12 = _obstacles;
final int groupLen12 = group12.getSize()
;int index12 = 0;
;
for (; index12 < groupLen12;index12++){
_obs = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group12.Get(index12)));
 //BA.debugLineNum = 323;BA.debugLine="If obs.Get(\"x\") = foodX And obs.Get(\"y\") = food";
if ((_obs.Get((Object)("x"))).equals((Object)(_foodx)) && (_obs.Get((Object)("y"))).equals((Object)(_foody))) { 
 //BA.debugLineNum = 324;BA.debugLine="validPos = False";
_validpos = __c.False;
 //BA.debugLineNum = 325;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 }
;
 //BA.debugLineNum = 329;BA.debugLine="End Sub";
return "";
}
public String  _startnewgame() throws Exception{
 //BA.debugLineNum = 76;BA.debugLine="Public Sub StartNewGame";
 //BA.debugLineNum = 77;BA.debugLine="ResetGameState";
_resetgamestate();
 //BA.debugLineNum = 78;BA.debugLine="IsActive = True";
_isactive = __c.True;
 //BA.debugLineNum = 79;BA.debugLine="IsPausedState = False";
_ispausedstate = __c.False;
 //BA.debugLineNum = 80;BA.debugLine="soundManager.StartMusic";
_soundmanager._startmusic /*String*/ ();
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
return "";
}
public String  _triggergameover() throws Exception{
 //BA.debugLineNum = 428;BA.debugLine="Private Sub TriggerGameOver";
 //BA.debugLineNum = 429;BA.debugLine="IsActive = False";
_isactive = __c.False;
 //BA.debugLineNum = 430;BA.debugLine="soundManager.StopMusic";
_soundmanager._stopmusic /*String*/ ();
 //BA.debugLineNum = 431;BA.debugLine="If currentScore > highScore Then highScore = curr";
if (_currentscore>_highscore) { 
_highscore = _currentscore;};
 //BA.debugLineNum = 432;BA.debugLine="CallSubDelayed2(Snake, \"HandleGameOver\", Array As";
__c.CallSubDelayed2(ba,(Object)(_snake.getObject()),"HandleGameOver",(Object)(new Object[]{(Object)(_currentscore),(Object)(_currentlevel)}));
 //BA.debugLineNum = 433;BA.debugLine="End Sub";
return "";
}
public String  _update() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _segment = null;
int _targetx = 0;
int _targety = 0;
float _currentx = 0f;
float _currenty = 0f;
float _newx = 0f;
float _newy = 0f;
float _headx = 0f;
float _heady = 0f;
anywheresoftware.b4a.objects.collections.Map _headsegment = null;
int _targetheadx = 0;
int _targetheady = 0;
 //BA.debugLineNum = 161;BA.debugLine="Public Sub Update";
 //BA.debugLineNum = 162;BA.debugLine="If Not(IsActive) Or IsPausedState Then Return";
if (__c.Not(_isactive) || _ispausedstate) { 
if (true) return "";};
 //BA.debugLineNum = 165;BA.debugLine="For i = 0 To snakeBody.Size - 1";
{
final int step2 = 1;
final int limit2 = (int) (_snakebody.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 166;BA.debugLine="Dim segment As Map = snakeBody.Get(i)";
_segment = new anywheresoftware.b4a.objects.collections.Map();
_segment = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_snakebody.Get(_i)));
 //BA.debugLineNum = 167;BA.debugLine="Dim targetX As Int = segment.Get(\"x\")";
_targetx = (int)(BA.ObjectToNumber(_segment.Get((Object)("x"))));
 //BA.debugLineNum = 168;BA.debugLine="Dim targetY As Int = segment.Get(\"y\")";
_targety = (int)(BA.ObjectToNumber(_segment.Get((Object)("y"))));
 //BA.debugLineNum = 169;BA.debugLine="Dim currentX As Float = snakeAnimX.Get(i)";
_currentx = (float)(BA.ObjectToNumber(_snakeanimx.Get(_i)));
 //BA.debugLineNum = 170;BA.debugLine="Dim currentY As Float = snakeAnimY.Get(i)";
_currenty = (float)(BA.ObjectToNumber(_snakeanimy.Get(_i)));
 //BA.debugLineNum = 173;BA.debugLine="Dim newX As Float = currentX + (targetX - curren";
_newx = (float) (_currentx+(_targetx-_currentx)*_anim_speed);
 //BA.debugLineNum = 174;BA.debugLine="Dim newY As Float = currentY + (targetY - curren";
_newy = (float) (_currenty+(_targety-_currenty)*_anim_speed);
 //BA.debugLineNum = 177;BA.debugLine="If Abs(targetX - newX) < 0.1 Then newX = targetX";
if (__c.Abs(_targetx-_newx)<0.1) { 
_newx = (float) (_targetx);};
 //BA.debugLineNum = 178;BA.debugLine="If Abs(targetY - newY) < 0.1 Then newY = targetY";
if (__c.Abs(_targety-_newy)<0.1) { 
_newy = (float) (_targety);};
 //BA.debugLineNum = 180;BA.debugLine="snakeAnimX.Set(i, newX)";
_snakeanimx.Set(_i,(Object)(_newx));
 //BA.debugLineNum = 181;BA.debugLine="snakeAnimY.Set(i, newY)";
_snakeanimy.Set(_i,(Object)(_newy));
 }
};
 //BA.debugLineNum = 185;BA.debugLine="Dim headX As Float = snakeAnimX.Get(0)";
_headx = (float)(BA.ObjectToNumber(_snakeanimx.Get((int) (0))));
 //BA.debugLineNum = 186;BA.debugLine="Dim headY As Float = snakeAnimY.Get(0)";
_heady = (float)(BA.ObjectToNumber(_snakeanimy.Get((int) (0))));
 //BA.debugLineNum = 187;BA.debugLine="Dim headSegment As Map = snakeBody.Get(0)";
_headsegment = new anywheresoftware.b4a.objects.collections.Map();
_headsegment = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_snakebody.Get((int) (0))));
 //BA.debugLineNum = 188;BA.debugLine="Dim targetHeadX As Int = headSegment.Get(\"x\")";
_targetheadx = (int)(BA.ObjectToNumber(_headsegment.Get((Object)("x"))));
 //BA.debugLineNum = 189;BA.debugLine="Dim targetHeadY As Int = headSegment.Get(\"y\")";
_targetheady = (int)(BA.ObjectToNumber(_headsegment.Get((Object)("y"))));
 //BA.debugLineNum = 191;BA.debugLine="If Abs(headX - targetHeadX) < 0.1 And Abs(headY -";
if (__c.Abs(_headx-_targetheadx)<0.1 && __c.Abs(_heady-_targetheady)<0.1) { 
 //BA.debugLineNum = 192;BA.debugLine="MoveSnake";
_movesnake();
 };
 //BA.debugLineNum = 195;BA.debugLine="UpdateParticles";
_updateparticles();
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public String  _updateobstacles() throws Exception{
int _i = 0;
boolean _validpos = false;
int _ox = 0;
int _oy = 0;
anywheresoftware.b4a.objects.collections.Map _segment = null;
anywheresoftware.b4a.objects.collections.Map _obs = null;
 //BA.debugLineNum = 109;BA.debugLine="Private Sub UpdateObstacles";
 //BA.debugLineNum = 110;BA.debugLine="obstacles.Clear";
_obstacles.Clear();
 //BA.debugLineNum = 113;BA.debugLine="obstacleCount = Min(15, (currentLevel - 1) * 2)";
_obstaclecount = (int) (__c.Min(15,(_currentlevel-1)*2));
 //BA.debugLineNum = 115;BA.debugLine="For i = 0 To obstacleCount - 1";
{
final int step3 = 1;
final int limit3 = (int) (_obstaclecount-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 116;BA.debugLine="Dim validPos As Boolean = False";
_validpos = __c.False;
 //BA.debugLineNum = 117;BA.debugLine="Dim ox As Int";
_ox = 0;
 //BA.debugLineNum = 118;BA.debugLine="Dim oy As Int";
_oy = 0;
 //BA.debugLineNum = 121;BA.debugLine="Do While Not(validPos)";
while (__c.Not(_validpos)) {
 //BA.debugLineNum = 122;BA.debugLine="ox = Rnd(2, GRID_WIDTH - 2)";
_ox = __c.Rnd((int) (2),(int) (_grid_width-2));
 //BA.debugLineNum = 123;BA.debugLine="oy = Rnd(2, GRID_HEIGHT - 2)";
_oy = __c.Rnd((int) (2),(int) (_grid_height-2));
 //BA.debugLineNum = 124;BA.debugLine="validPos = True";
_validpos = __c.True;
 //BA.debugLineNum = 127;BA.debugLine="If ox = foodX And oy = foodY Then validPos = Fa";
if (_ox==_foodx && _oy==_foody) { 
_validpos = __c.False;};
 //BA.debugLineNum = 128;BA.debugLine="For Each segment As Map In snakeBody";
_segment = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group12 = _snakebody;
final int groupLen12 = group12.getSize()
;int index12 = 0;
;
for (; index12 < groupLen12;index12++){
_segment = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group12.Get(index12)));
 //BA.debugLineNum = 129;BA.debugLine="If segment.Get(\"x\") = ox And segment.Get(\"y\")";
if ((_segment.Get((Object)("x"))).equals((Object)(_ox)) && (_segment.Get((Object)("y"))).equals((Object)(_oy))) { 
 //BA.debugLineNum = 130;BA.debugLine="validPos = False";
_validpos = __c.False;
 //BA.debugLineNum = 131;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 136;BA.debugLine="For Each obs As Map In obstacles";
_obs = new anywheresoftware.b4a.objects.collections.Map();
{
final anywheresoftware.b4a.BA.IterableList group18 = _obstacles;
final int groupLen18 = group18.getSize()
;int index18 = 0;
;
for (; index18 < groupLen18;index18++){
_obs = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(group18.Get(index18)));
 //BA.debugLineNum = 137;BA.debugLine="If Abs(obs.Get(\"x\") - ox) < 2 And Abs(obs.Get(";
if (__c.Abs((double)(BA.ObjectToNumber(_obs.Get((Object)("x"))))-_ox)<2 && __c.Abs((double)(BA.ObjectToNumber(_obs.Get((Object)("y"))))-_oy)<2) { 
 //BA.debugLineNum = 138;BA.debugLine="validPos = False";
_validpos = __c.False;
 //BA.debugLineNum = 139;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 }
;
 //BA.debugLineNum = 144;BA.debugLine="obstacles.Add(CreatePoint(ox, oy))";
_obstacles.Add((Object)(_createpoint(_ox,_oy).getObject()));
 }
};
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public String  _updateparticles() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _p = null;
int _life = 0;
 //BA.debugLineNum = 247;BA.debugLine="Private Sub UpdateParticles";
 //BA.debugLineNum = 248;BA.debugLine="For i = particles.Size - 1 To 0 Step -1";
{
final int step1 = -1;
final int limit1 = (int) (0);
_i = (int) (_particles.getSize()-1) ;
for (;_i >= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 249;BA.debugLine="Dim p As Map = particles.Get(i)";
_p = new anywheresoftware.b4a.objects.collections.Map();
_p = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_particles.Get(_i)));
 //BA.debugLineNum = 250;BA.debugLine="Dim life As Int = p.Get(\"life\") - 1";
_life = (int) ((double)(BA.ObjectToNumber(_p.Get((Object)("life"))))-1);
 //BA.debugLineNum = 252;BA.debugLine="If life <= 0 Then";
if (_life<=0) { 
 //BA.debugLineNum = 253;BA.debugLine="particles.RemoveAt(i)";
_particles.RemoveAt(_i);
 }else {
 //BA.debugLineNum = 255;BA.debugLine="p.Put(\"x\", p.Get(\"x\") + p.Get(\"vx\"))";
_p.Put((Object)("x"),(Object)((double)(BA.ObjectToNumber(_p.Get((Object)("x"))))+(double)(BA.ObjectToNumber(_p.Get((Object)("vx"))))));
 //BA.debugLineNum = 256;BA.debugLine="p.Put(\"y\", p.Get(\"y\") + p.Get(\"vy\"))";
_p.Put((Object)("y"),(Object)((double)(BA.ObjectToNumber(_p.Get((Object)("y"))))+(double)(BA.ObjectToNumber(_p.Get((Object)("vy"))))));
 //BA.debugLineNum = 257;BA.debugLine="p.Put(\"vy\", p.Get(\"vy\") + 0.1)  ' Gravity";
_p.Put((Object)("vy"),(Object)((double)(BA.ObjectToNumber(_p.Get((Object)("vy"))))+0.1));
 //BA.debugLineNum = 258;BA.debugLine="p.Put(\"life\", life)";
_p.Put((Object)("life"),(Object)(_life));
 };
 }
};
 //BA.debugLineNum = 261;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
