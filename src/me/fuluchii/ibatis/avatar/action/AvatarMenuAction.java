package me.fuluchii.ibatis.avatar.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import me.fuluchii.ibatis.avatar.model.TableModel;

public class AvatarMenuAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        Project project = (Project)e.getData(PlatformDataKeys.PROJECT);


        String tablename = Messages.showInputDialog(project,"Input Table Name:","tablename",Messages.getQuestionIcon());
        String basePackage = Messages.showInputDialog(project,"Input Base Package:","package name",Messages.getQuestionIcon());
        String tableInfo = Messages.showInputDialog(project,"Input Table Info:","tableinfo",Messages.getQuestionIcon());
        TableModel model = new TableModel(tablename,basePackage,tableInfo);
        final Application application = ApplicationManager.getApplication();
        //run write action
        Runnable runnable = new CreateFileTask(project,model);
        application.runWriteAction(runnable);


    }
}
