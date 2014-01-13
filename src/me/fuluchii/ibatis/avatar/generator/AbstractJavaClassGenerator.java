package me.fuluchii.ibatis.avatar.generator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.search.GlobalSearchScope;
import me.fuluchii.ibatis.avatar.model.TableModel;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @author fuluchii.zhao
 */
public abstract class AbstractJavaClassGenerator {
    protected Project project;

    protected PsiElementFactory psiElementFactory;

    protected JavaPsiFacade javaPsiFacade;

    protected PsiClass target;

    public AbstractJavaClassGenerator(Project project){
        this.project = project;
        javaPsiFacade = JavaPsiFacade.getInstance(project);
    }

    public boolean buildJavaClass(TableModel tableModel,String className){
        if(validate(tableModel)){
        createClassFile(tableModel,className);
        modifyClassFile(tableModel);
        formatCode();
        }
        return true;
    }

    protected boolean validate(TableModel tableModel){
        return true;
    }

    protected abstract void addInterface();

    protected void createClassFile(TableModel tableModel,String name) {
        JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);

        PsiPackage targetPackage = javaPsiFacade.findPackage(tableModel.getBasePackage());
        psiElementFactory = PsiElementFactory.SERVICE.getInstance(project);
        target = JavaDirectoryService.getInstance().createClass(targetPackage.getDirectories()[0],name);
    }



    protected void modifyClassFile(TableModel tableModel) {
        addFields(tableModel);
        addGetters(tableModel);
        addSetters(tableModel);
        addInterface();
        addImportList(tableModel);
    }




    protected void formatCode() {
        CodeStyleManager.getInstance(project).reformat(target);
    }

    private void addFields(TableModel tableModel){
        Map<String,String> filedsStr = tableModel.getFiledMap();
        for (String fieldName : filedsStr.keySet()) {
            PsiField field = PsiElementFactory.SERVICE.getInstance(project).createField(fieldName,getPsiType(filedsStr.get(fieldName)));
            target.add(field);
        }
    }

    private void addImportList(TableModel tableModel){
        Map<String,String> filedsStr = tableModel.getFiledMap();
        for (String fieldName : filedsStr.keySet()) {
        }
    }

    private void addGetters(TableModel tableModel){
        Map<String,String> filedsStr = tableModel.getFiledMap();
        for (String fieldName : filedsStr.keySet()) {
            PsiMethod getter = generateGetter(fieldName,getPsiType(filedsStr.get(fieldName)));
            target.add(getter);
        }
    }

    private void addSetters(TableModel tableModel){
        Map<String,String> filedsStr = tableModel.getFiledMap();
        for (String fieldName : filedsStr.keySet()) {
            PsiMethod getter = generateSetter(fieldName,getPsiType(filedsStr.get(fieldName)));
            target.add(getter);
        }
    }

    private PsiMethod generateGetter(String name,PsiType type){
        PsiStatement returnStatement = psiElementFactory.createStatementFromText("return this."+name+";",null);
        PsiMethod psiMethod = PsiElementFactory.SERVICE.getInstance(project).createMethod("get"+ StringUtils.capitalize(name),type);
        psiMethod.getBody().add(returnStatement);
        return psiMethod;
    }

    private PsiMethod generateSetter(String name,PsiType type){
        PsiParameter parameter = psiElementFactory.createParameter(name,type);

        PsiStatement setStatement = psiElementFactory.createStatementFromText("this."+name+"="+name+";",null);
        PsiMethod psiMethod = PsiElementFactory.SERVICE.getInstance(project).createMethod("set"+StringUtils.capitalize(name),PsiType.VOID);
        psiMethod.getBody().add(setStatement);
        psiMethod.getParameterList().add(parameter);
        return psiMethod;
    }



    private PsiType getPsiType(String type){
        if(StringUtils.equalsIgnoreCase(type, "varchar")){
            return PsiType.getJavaLangString(PsiManager.getInstance(project), GlobalSearchScope.allScope(project));
        }else if(StringUtils.equalsIgnoreCase(type,"int")){
            return PsiType.INT;
        }else if(StringUtils.equalsIgnoreCase(type,"boolean")){
            return PsiType.BOOLEAN;
        }else if(StringUtils.equalsIgnoreCase(type,"datetime")){
            return psiElementFactory.createType(JavaPsiFacade.getInstance(project).findClass("java.utils.Date",GlobalSearchScope.allScope(project)));
        }else if(StringUtils.equalsIgnoreCase(type,"double")){
            return PsiType.DOUBLE;
        }
        return null;
    }

}
