package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class collisionmanager extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.collisionmanager");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.collisionmanager.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public b4a.example.grid _grid = null;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private grid As Grid";
_grid = new b4a.example.grid();
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,b4a.example.grid _gamegrid) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 9;BA.debugLine="Public Sub Initialize(gameGrid As Grid)";
 //BA.debugLineNum = 10;BA.debugLine="grid = gameGrid";
_grid = _gamegrid;
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public boolean  _isvalidposition(int _piecex,int _piecey,int[][] _shape) throws Exception{
int _row = 0;
int _col = 0;
int _gridx = 0;
int _gridy = 0;
 //BA.debugLineNum = 16;BA.debugLine="Public Sub IsValidPosition(pieceX As Int, pieceY A";
 //BA.debugLineNum = 17;BA.debugLine="For row = 0 To 3";
{
final int step1 = 1;
final int limit1 = (int) (3);
_row = (int) (0) ;
for (;_row <= limit1 ;_row = _row + step1 ) {
 //BA.debugLineNum = 18;BA.debugLine="For col = 0 To 3";
{
final int step2 = 1;
final int limit2 = (int) (3);
_col = (int) (0) ;
for (;_col <= limit2 ;_col = _col + step2 ) {
 //BA.debugLineNum = 19;BA.debugLine="Try";
try { //BA.debugLineNum = 20;BA.debugLine="If shape(col, row) = 1 Then";
if (_shape[_col][_row]==1) { 
 //BA.debugLineNum = 21;BA.debugLine="Dim gridX As Int = pieceX + col";
_gridx = (int) (_piecex+_col);
 //BA.debugLineNum = 22;BA.debugLine="Dim gridY As Int = pieceY + row";
_gridy = (int) (_piecey+_row);
 //BA.debugLineNum = 25;BA.debugLine="If gridX < 0 Or gridX >= grid.GetWidth Then";
if (_gridx<0 || _gridx>=_grid._getwidth /*int*/ ()) { 
 //BA.debugLineNum = 26;BA.debugLine="Return False";
if (true) return __c.False;
 };
 //BA.debugLineNum = 30;BA.debugLine="If gridY >= grid.GetHeight Then";
if (_gridy>=_grid._getheight /*int*/ ()) { 
 //BA.debugLineNum = 31;BA.debugLine="Return False";
if (true) return __c.False;
 };
 //BA.debugLineNum = 35;BA.debugLine="If gridY >= 0 And grid.GetCell(gridX, gridY)";
if (_gridy>=0 && _grid._getcell /*int*/ (_gridx,_gridy)>0) { 
 //BA.debugLineNum = 36;BA.debugLine="Return False";
if (true) return __c.False;
 };
 };
 } 
       catch (Exception e18) {
			ba.setLastException(e18); };
 }
};
 }
};
 //BA.debugLineNum = 45;BA.debugLine="Return True";
if (true) return __c.True;
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return false;
}
public boolean  _wouldcollidebelow(int _piecex,int _piecey,int[][] _shape) throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Public Sub WouldCollideBelow(pieceX As Int, pieceY";
 //BA.debugLineNum = 49;BA.debugLine="Return Not(IsValidPosition(pieceX, pieceY + 1, sh";
if (true) return __c.Not(_isvalidposition(_piecex,(int) (_piecey+1),_shape));
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
return false;
}
public boolean  _wouldcollideleft(int _piecex,int _piecey,int[][] _shape) throws Exception{
 //BA.debugLineNum = 52;BA.debugLine="Public Sub WouldCollideLeft(pieceX As Int, pieceY";
 //BA.debugLineNum = 53;BA.debugLine="Return Not(IsValidPosition(pieceX - 1, pieceY, sh";
if (true) return __c.Not(_isvalidposition((int) (_piecex-1),_piecey,_shape));
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return false;
}
public boolean  _wouldcollideright(int _piecex,int _piecey,int[][] _shape) throws Exception{
 //BA.debugLineNum = 56;BA.debugLine="Public Sub WouldCollideRight(pieceX As Int, pieceY";
 //BA.debugLineNum = 57;BA.debugLine="Return Not(IsValidPosition(pieceX + 1, pieceY, sh";
if (true) return __c.Not(_isvalidposition((int) (_piecex+1),_piecey,_shape));
 //BA.debugLineNum = 58;BA.debugLine="End Sub";
return false;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
