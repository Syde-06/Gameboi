package b4a.example.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_mainlayout{

public static void LS_general(anywheresoftware.b4a.BA ba, android.view.View parent, anywheresoftware.b4a.keywords.LayoutValues lv, java.util.Map props,
java.util.Map<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) throws Exception {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
views.get("pnlgameboard").vw.setWidth((int)((300d * scale)));
views.get("pnlgameboard").vw.setHeight((int)((600d * scale)));
views.get("pnltetris").vw.setTop((int)((40d / 100 * width)));
views.get("pnltetris").vw.setLeft((int)((10d * scale)));
views.get("pnltetris").vw.setWidth((int)((55d / 100 * width)));
//BA.debugLineNum = 7;BA.debugLine="lblTetris.Width = 50%x"[MainLayout/General script]
views.get("lbltetris").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 8;BA.debugLine="pnlSnake.Top = 50%x"[MainLayout/General script]
views.get("pnlsnake").vw.setTop((int)((50d / 100 * width)));
//BA.debugLineNum = 9;BA.debugLine="pnlSnake.Left = 10dip"[MainLayout/General script]
views.get("pnlsnake").vw.setLeft((int)((10d * scale)));
//BA.debugLineNum = 10;BA.debugLine="pnlSnake.Width = 55%x"[MainLayout/General script]
views.get("pnlsnake").vw.setWidth((int)((55d / 100 * width)));
//BA.debugLineNum = 11;BA.debugLine="lblSnake.Width = 50%x"[MainLayout/General script]
views.get("lblsnake").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 12;BA.debugLine="pnlSpaceInvaders.Top = 60%x"[MainLayout/General script]
views.get("pnlspaceinvaders").vw.setTop((int)((60d / 100 * width)));
//BA.debugLineNum = 13;BA.debugLine="pnlSpaceInvaders.Left = 10dip"[MainLayout/General script]
views.get("pnlspaceinvaders").vw.setLeft((int)((10d * scale)));
//BA.debugLineNum = 14;BA.debugLine="pnlSpaceInvaders.Width = 55%x"[MainLayout/General script]
views.get("pnlspaceinvaders").vw.setWidth((int)((55d / 100 * width)));
//BA.debugLineNum = 15;BA.debugLine="lblSpaceInvaders.Width = 50%x"[MainLayout/General script]
views.get("lblspaceinvaders").vw.setWidth((int)((50d / 100 * width)));
//BA.debugLineNum = 17;BA.debugLine="lblScore.Height = 50dip"[MainLayout/General script]
views.get("lblscore").vw.setHeight((int)((50d * scale)));
//BA.debugLineNum = 18;BA.debugLine="lblScore.TextSize = 12"[MainLayout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblscore").vw).setTextSize((float)(12d));
//BA.debugLineNum = 19;BA.debugLine="lblLevel.Height = 50dip"[MainLayout/General script]
views.get("lbllevel").vw.setHeight((int)((50d * scale)));
//BA.debugLineNum = 20;BA.debugLine="lblLevel.TextSize = 12"[MainLayout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lbllevel").vw).setTextSize((float)(12d));
//BA.debugLineNum = 21;BA.debugLine="lblLines.Height = 50dip"[MainLayout/General script]
views.get("lbllines").vw.setHeight((int)((50d * scale)));
//BA.debugLineNum = 22;BA.debugLine="lblLines.TextSize = 12"[MainLayout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lbllines").vw).setTextSize((float)(12d));
//BA.debugLineNum = 24;BA.debugLine="pnlNextPiece.Height = 150dip"[MainLayout/General script]
views.get("pnlnextpiece").vw.setHeight((int)((150d * scale)));
//BA.debugLineNum = 25;BA.debugLine="lblNextPiece.TextSize = 12"[MainLayout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblnextpiece").vw).setTextSize((float)(12d));
//BA.debugLineNum = 27;BA.debugLine="lblPause.TextSize = 12"[MainLayout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblpause").vw).setTextSize((float)(12d));
//BA.debugLineNum = 28;BA.debugLine="lblRestart.TextSize = 12"[MainLayout/General script]
((anywheresoftware.b4a.keywords.LayoutBuilder.DesignerTextSizeMethod)views.get("lblrestart").vw).setTextSize((float)(12d));
//BA.debugLineNum = 30;BA.debugLine="lblScore.Top = 20"[MainLayout/General script]
views.get("lblscore").vw.setTop((int)(20d));
//BA.debugLineNum = 31;BA.debugLine="lblLevel.Top = lblScore.Bottom"[MainLayout/General script]
views.get("lbllevel").vw.setTop((int)((views.get("lblscore").vw.getTop() + views.get("lblscore").vw.getHeight())));
//BA.debugLineNum = 32;BA.debugLine="lblLines.Top = lblLevel.Bottom"[MainLayout/General script]
views.get("lbllines").vw.setTop((int)((views.get("lbllevel").vw.getTop() + views.get("lbllevel").vw.getHeight())));
//BA.debugLineNum = 33;BA.debugLine="pnlNextPiece.Top = lblLines.Bottom"[MainLayout/General script]
views.get("pnlnextpiece").vw.setTop((int)((views.get("lbllines").vw.getTop() + views.get("lbllines").vw.getHeight())));
//BA.debugLineNum = 34;BA.debugLine="lblPause.Top = pnlNextPiece.Bottom"[MainLayout/General script]
views.get("lblpause").vw.setTop((int)((views.get("pnlnextpiece").vw.getTop() + views.get("pnlnextpiece").vw.getHeight())));
//BA.debugLineNum = 35;BA.debugLine="btnPause.Top = lblPause.Bottom"[MainLayout/General script]
views.get("btnpause").vw.setTop((int)((views.get("lblpause").vw.getTop() + views.get("lblpause").vw.getHeight())));
//BA.debugLineNum = 36;BA.debugLine="lblRestart.Top = btnPause.Bottom"[MainLayout/General script]
views.get("lblrestart").vw.setTop((int)((views.get("btnpause").vw.getTop() + views.get("btnpause").vw.getHeight())));
//BA.debugLineNum = 37;BA.debugLine="btnRestart.Top = lblRestart.Bottom"[MainLayout/General script]
views.get("btnrestart").vw.setTop((int)((views.get("lblrestart").vw.getTop() + views.get("lblrestart").vw.getHeight())));
//BA.debugLineNum = 38;BA.debugLine="lblLeaderboard.Top = btnRestart.Bottom"[MainLayout/General script]
views.get("lblleaderboard").vw.setTop((int)((views.get("btnrestart").vw.getTop() + views.get("btnrestart").vw.getHeight())));
//BA.debugLineNum = 39;BA.debugLine="btnLeaderboard.Top = lblLeaderboard.Bottom"[MainLayout/General script]
views.get("btnleaderboard").vw.setTop((int)((views.get("lblleaderboard").vw.getTop() + views.get("lblleaderboard").vw.getHeight())));
//BA.debugLineNum = 40;BA.debugLine="lblSettings.Top = btnLeaderboard.Bottom"[MainLayout/General script]
views.get("lblsettings").vw.setTop((int)((views.get("btnleaderboard").vw.getTop() + views.get("btnleaderboard").vw.getHeight())));
//BA.debugLineNum = 41;BA.debugLine="btnSettings.Top = lblSettings.Bottom"[MainLayout/General script]
views.get("btnsettings").vw.setTop((int)((views.get("lblsettings").vw.getTop() + views.get("lblsettings").vw.getHeight())));
//BA.debugLineNum = 42;BA.debugLine="pnlGameBoard.Top = 20"[MainLayout/General script]
views.get("pnlgameboard").vw.setTop((int)(20d));
//BA.debugLineNum = 43;BA.debugLine="pnlGameBoard.Left = 20"[MainLayout/General script]
views.get("pnlgameboard").vw.setLeft((int)(20d));
//BA.debugLineNum = 44;BA.debugLine="pnlControls.Top = pnlGameBoard.Bottom + 10"[MainLayout/General script]
views.get("pnlcontrols").vw.setTop((int)((views.get("pnlgameboard").vw.getTop() + views.get("pnlgameboard").vw.getHeight())+10d));
//BA.debugLineNum = 45;BA.debugLine="pnlControls.Left = 20"[MainLayout/General script]
views.get("pnlcontrols").vw.setLeft((int)(20d));
//BA.debugLineNum = 48;BA.debugLine="AutoScaleAll"[MainLayout/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);

}
}