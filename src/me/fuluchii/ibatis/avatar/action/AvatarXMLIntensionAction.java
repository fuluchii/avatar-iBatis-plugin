package me.fuluchii.ibatis.avatar.action;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import me.fuluchii.ibatis.avatar.data.AvatarPluginData;
import org.jetbrains.annotations.NotNull;

public class AvatarXMLIntensionAction implements IntentionAction {

    @NotNull
    @Override
    public String getText() {
        return AvatarPluginData.INSERT_STATEMENT_DEFAULT_TITLE;
    }

    @NotNull
    @Override
    public String getFamilyName() {
        return getText();
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}
