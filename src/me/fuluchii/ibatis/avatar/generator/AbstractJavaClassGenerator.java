package me.fuluchii.ibatis.avatar.generator;

import com.intellij.psi.PsiClass;
import me.fuluchii.ibatis.avatar.model.TableModel;

/**
 * @author fuluchii.zhao
 */
public abstract class AbstractJavaClassGenerator {

    protected PsiClass target;
    public boolean buildJavaClass(TableModel tableModel){
        if(validate(tableModel)){
        createClassFile(tableModel,tableModel.getBeanName());
        modifyClassFile(tableModel);
        formatCode();
        }
        return true;
    }

    protected abstract void createClassFile(TableModel tableModel,String name);

    protected abstract void modifyClassFile(TableModel tableModel);

    protected boolean validate(TableModel tableModel){
        return true;
    }

    protected abstract void formatCode();


}
