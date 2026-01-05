package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class piecefactory extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.example.piecefactory");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.piecefactory.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public int _type_i = 0;
public int _type_o = 0;
public int _type_t = 0;
public int _type_s = 0;
public int _type_z = 0;
public int _type_j = 0;
public int _type_l = 0;
public b4a.example.main _main = null;
public b4a.example.tetris _tetris = null;
public b4a.example.snake _snake = null;
public b4a.example.spaceinvaders _spaceinvaders = null;
public b4a.example.lb _lb = null;
public b4a.example.starter _starter = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private Const TYPE_I As Int = 1";
_type_i = (int) (1);
 //BA.debugLineNum = 8;BA.debugLine="Private Const TYPE_O As Int = 2";
_type_o = (int) (2);
 //BA.debugLineNum = 9;BA.debugLine="Private Const TYPE_T As Int = 3";
_type_t = (int) (3);
 //BA.debugLineNum = 10;BA.debugLine="Private Const TYPE_S As Int = 4";
_type_s = (int) (4);
 //BA.debugLineNum = 11;BA.debugLine="Private Const TYPE_Z As Int = 5";
_type_z = (int) (5);
 //BA.debugLineNum = 12;BA.debugLine="Private Const TYPE_J As Int = 6";
_type_j = (int) (6);
 //BA.debugLineNum = 13;BA.debugLine="Private Const TYPE_L As Int = 7";
_type_l = (int) (7);
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public int[][]  _createishape() throws Exception{
int[][] _s = null;
int _i = 0;
int _j = 0;
 //BA.debugLineNum = 52;BA.debugLine="Private Sub CreateIShape As Int(,)";
 //BA.debugLineNum = 54;BA.debugLine="Dim s(4, 4) As Int";
_s = new int[(int) (4)][];
{
int d0 = _s.length;
int d1 = (int) (4);
for (int i0 = 0;i0 < d0;i0++) {
_s[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 55;BA.debugLine="For i = 0 To 3";
{
final int step2 = 1;
final int limit2 = (int) (3);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 56;BA.debugLine="For j = 0 To 3";
{
final int step3 = 1;
final int limit3 = (int) (3);
_j = (int) (0) ;
for (;_j <= limit3 ;_j = _j + step3 ) {
 //BA.debugLineNum = 57;BA.debugLine="s(i, j) = 0";
_s[_i][_j] = (int) (0);
 }
};
 }
};
 //BA.debugLineNum = 61;BA.debugLine="s(0, 1) = 1";
_s[(int) (0)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 62;BA.debugLine="s(1, 1) = 1";
_s[(int) (1)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 63;BA.debugLine="s(2, 1) = 1";
_s[(int) (2)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 64;BA.debugLine="s(3, 1) = 1";
_s[(int) (3)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 66;BA.debugLine="Return s";
if (true) return _s;
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return null;
}
public int[][]  _createjshape() throws Exception{
int[][] _s = null;
int _i = 0;
int _j = 0;
 //BA.debugLineNum = 134;BA.debugLine="Private Sub CreateJShape As Int(,)";
 //BA.debugLineNum = 137;BA.debugLine="Dim s(3, 2) As Int";
_s = new int[(int) (3)][];
{
int d0 = _s.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_s[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 138;BA.debugLine="For i = 0 To 2";
{
final int step2 = 1;
final int limit2 = (int) (2);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 139;BA.debugLine="For j = 0 To 1";
{
final int step3 = 1;
final int limit3 = (int) (1);
_j = (int) (0) ;
for (;_j <= limit3 ;_j = _j + step3 ) {
 //BA.debugLineNum = 140;BA.debugLine="s(i, j) = 0";
_s[_i][_j] = (int) (0);
 }
};
 }
};
 //BA.debugLineNum = 144;BA.debugLine="s(0, 0) = 1";
_s[(int) (0)][(int) (0)] = (int) (1);
 //BA.debugLineNum = 145;BA.debugLine="s(0, 1) = 1";
_s[(int) (0)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 146;BA.debugLine="s(1, 1) = 1";
_s[(int) (1)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 147;BA.debugLine="s(2, 1) = 1";
_s[(int) (2)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 149;BA.debugLine="Return s";
if (true) return _s;
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return null;
}
public int[][]  _createlshape() throws Exception{
int[][] _s = null;
int _i = 0;
int _j = 0;
 //BA.debugLineNum = 152;BA.debugLine="Private Sub CreateLShape As Int(,)";
 //BA.debugLineNum = 155;BA.debugLine="Dim s(3, 2) As Int";
_s = new int[(int) (3)][];
{
int d0 = _s.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_s[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 156;BA.debugLine="For i = 0 To 2";
{
final int step2 = 1;
final int limit2 = (int) (2);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 157;BA.debugLine="For j = 0 To 1";
{
final int step3 = 1;
final int limit3 = (int) (1);
_j = (int) (0) ;
for (;_j <= limit3 ;_j = _j + step3 ) {
 //BA.debugLineNum = 158;BA.debugLine="s(i, j) = 0";
_s[_i][_j] = (int) (0);
 }
};
 }
};
 //BA.debugLineNum = 162;BA.debugLine="s(2, 0) = 1";
_s[(int) (2)][(int) (0)] = (int) (1);
 //BA.debugLineNum = 163;BA.debugLine="s(0, 1) = 1";
_s[(int) (0)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 164;BA.debugLine="s(1, 1) = 1";
_s[(int) (1)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 165;BA.debugLine="s(2, 1) = 1";
_s[(int) (2)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 167;BA.debugLine="Return s";
if (true) return _s;
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return null;
}
public int[][]  _createoshape() throws Exception{
int[][] _s = null;
 //BA.debugLineNum = 69;BA.debugLine="Private Sub CreateOShape As Int(,)";
 //BA.debugLineNum = 72;BA.debugLine="Dim s(2, 2) As Int";
_s = new int[(int) (2)][];
{
int d0 = _s.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_s[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 73;BA.debugLine="s(0, 0) = 1";
_s[(int) (0)][(int) (0)] = (int) (1);
 //BA.debugLineNum = 74;BA.debugLine="s(1, 0) = 1";
_s[(int) (1)][(int) (0)] = (int) (1);
 //BA.debugLineNum = 75;BA.debugLine="s(0, 1) = 1";
_s[(int) (0)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 76;BA.debugLine="s(1, 1) = 1";
_s[(int) (1)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 77;BA.debugLine="Return s";
if (true) return _s;
 //BA.debugLineNum = 78;BA.debugLine="End Sub";
return null;
}
public b4a.example.tetromino  _createpieceoftype(int _piecetype) throws Exception{
b4a.example.tetromino _piece = null;
int[][] _shape = null;
 //BA.debugLineNum = 28;BA.debugLine="Public Sub CreatePieceOfType(pieceType As Int) As";
 //BA.debugLineNum = 29;BA.debugLine="Dim piece As Tetromino";
_piece = new b4a.example.tetromino();
 //BA.debugLineNum = 30;BA.debugLine="piece.Initialize";
_piece._initialize /*String*/ (ba);
 //BA.debugLineNum = 32;BA.debugLine="Dim shape(,) As Int";
_shape = new int[(int) (0)][];
{
int d0 = _shape.length;
int d1 = (int) (0);
for (int i0 = 0;i0 < d0;i0++) {
_shape[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 34;BA.debugLine="Select pieceType";
switch (BA.switchObjectToInt(_piecetype,_type_i,_type_o,_type_t,_type_s,_type_z,_type_j,_type_l)) {
case 0: {
 //BA.debugLineNum = 35;BA.debugLine="Case TYPE_I: shape = CreateIShape";
_shape = _createishape();
 break; }
case 1: {
 //BA.debugLineNum = 36;BA.debugLine="Case TYPE_O: shape = CreateOShape";
_shape = _createoshape();
 break; }
case 2: {
 //BA.debugLineNum = 37;BA.debugLine="Case TYPE_T: shape = CreateTShape";
_shape = _createtshape();
 break; }
case 3: {
 //BA.debugLineNum = 38;BA.debugLine="Case TYPE_S: shape = CreateSShape";
_shape = _createsshape();
 break; }
case 4: {
 //BA.debugLineNum = 39;BA.debugLine="Case TYPE_Z: shape = CreateZShape";
_shape = _createzshape();
 break; }
case 5: {
 //BA.debugLineNum = 40;BA.debugLine="Case TYPE_J: shape = CreateJShape";
_shape = _createjshape();
 break; }
case 6: {
 //BA.debugLineNum = 41;BA.debugLine="Case TYPE_L: shape = CreateLShape";
_shape = _createlshape();
 break; }
default: {
 //BA.debugLineNum = 42;BA.debugLine="Case Else: shape = CreateTShape";
_shape = _createtshape();
 break; }
}
;
 //BA.debugLineNum = 45;BA.debugLine="piece.Initialize2(pieceType, shape)";
_piece._initialize2 /*String*/ (_piecetype,_shape);
 //BA.debugLineNum = 46;BA.debugLine="Return piece";
if (true) return _piece;
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return null;
}
public b4a.example.tetromino  _createrandompiece() throws Exception{
int _piecetype = 0;
 //BA.debugLineNum = 23;BA.debugLine="Public Sub CreateRandomPiece As Tetromino";
 //BA.debugLineNum = 24;BA.debugLine="Dim pieceType As Int = Rnd(1, 8)  ' 1 to 7";
_piecetype = __c.Rnd((int) (1),(int) (8));
 //BA.debugLineNum = 25;BA.debugLine="Return CreatePieceOfType(pieceType)";
if (true) return _createpieceoftype(_piecetype);
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return null;
}
public int[][]  _createsshape() throws Exception{
int[][] _s = null;
int _i = 0;
int _j = 0;
 //BA.debugLineNum = 98;BA.debugLine="Private Sub CreateSShape As Int(,)";
 //BA.debugLineNum = 101;BA.debugLine="Dim s(3, 2) As Int";
_s = new int[(int) (3)][];
{
int d0 = _s.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_s[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 102;BA.debugLine="For i = 0 To 2";
{
final int step2 = 1;
final int limit2 = (int) (2);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 103;BA.debugLine="For j = 0 To 1";
{
final int step3 = 1;
final int limit3 = (int) (1);
_j = (int) (0) ;
for (;_j <= limit3 ;_j = _j + step3 ) {
 //BA.debugLineNum = 104;BA.debugLine="s(i, j) = 0";
_s[_i][_j] = (int) (0);
 }
};
 }
};
 //BA.debugLineNum = 108;BA.debugLine="s(1, 0) = 1";
_s[(int) (1)][(int) (0)] = (int) (1);
 //BA.debugLineNum = 109;BA.debugLine="s(2, 0) = 1";
_s[(int) (2)][(int) (0)] = (int) (1);
 //BA.debugLineNum = 110;BA.debugLine="s(0, 1) = 1";
_s[(int) (0)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 111;BA.debugLine="s(1, 1) = 1";
_s[(int) (1)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 113;BA.debugLine="Return s";
if (true) return _s;
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return null;
}
public int[][]  _createtshape() throws Exception{
int[][] _s = null;
int _i = 0;
int _j = 0;
 //BA.debugLineNum = 80;BA.debugLine="Private Sub CreateTShape As Int(,)";
 //BA.debugLineNum = 83;BA.debugLine="Dim s(3, 2) As Int";
_s = new int[(int) (3)][];
{
int d0 = _s.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_s[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 84;BA.debugLine="For i = 0 To 2";
{
final int step2 = 1;
final int limit2 = (int) (2);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 85;BA.debugLine="For j = 0 To 1";
{
final int step3 = 1;
final int limit3 = (int) (1);
_j = (int) (0) ;
for (;_j <= limit3 ;_j = _j + step3 ) {
 //BA.debugLineNum = 86;BA.debugLine="s(i, j) = 0";
_s[_i][_j] = (int) (0);
 }
};
 }
};
 //BA.debugLineNum = 90;BA.debugLine="s(1, 0) = 1";
_s[(int) (1)][(int) (0)] = (int) (1);
 //BA.debugLineNum = 91;BA.debugLine="s(0, 1) = 1";
_s[(int) (0)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 92;BA.debugLine="s(1, 1) = 1";
_s[(int) (1)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 93;BA.debugLine="s(2, 1) = 1";
_s[(int) (2)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 95;BA.debugLine="Return s";
if (true) return _s;
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return null;
}
public int[][]  _createzshape() throws Exception{
int[][] _s = null;
int _i = 0;
int _j = 0;
 //BA.debugLineNum = 116;BA.debugLine="Private Sub CreateZShape As Int(,)";
 //BA.debugLineNum = 119;BA.debugLine="Dim s(3, 2) As Int";
_s = new int[(int) (3)][];
{
int d0 = _s.length;
int d1 = (int) (2);
for (int i0 = 0;i0 < d0;i0++) {
_s[i0] = new int[d1];
}
}
;
 //BA.debugLineNum = 120;BA.debugLine="For i = 0 To 2";
{
final int step2 = 1;
final int limit2 = (int) (2);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 121;BA.debugLine="For j = 0 To 1";
{
final int step3 = 1;
final int limit3 = (int) (1);
_j = (int) (0) ;
for (;_j <= limit3 ;_j = _j + step3 ) {
 //BA.debugLineNum = 122;BA.debugLine="s(i, j) = 0";
_s[_i][_j] = (int) (0);
 }
};
 }
};
 //BA.debugLineNum = 126;BA.debugLine="s(0, 0) = 1";
_s[(int) (0)][(int) (0)] = (int) (1);
 //BA.debugLineNum = 127;BA.debugLine="s(1, 0) = 1";
_s[(int) (1)][(int) (0)] = (int) (1);
 //BA.debugLineNum = 128;BA.debugLine="s(1, 1) = 1";
_s[(int) (1)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 129;BA.debugLine="s(2, 1) = 1";
_s[(int) (2)][(int) (1)] = (int) (1);
 //BA.debugLineNum = 131;BA.debugLine="Return s";
if (true) return _s;
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return null;
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 16;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
