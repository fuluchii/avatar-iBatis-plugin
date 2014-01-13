package me.fuluchii.ibatis.avatar.generator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiJavaCodeReferenceElement;
import com.intellij.psi.search.GlobalSearchScope;

/**
 * @author fuluchii.zhao
 */
public class JavaDtoGenerator extends AbstractJavaClassGenerator{


    public JavaDtoGenerator(Project project) {
        super(project);
    }

    protected void addInterface(){
        PsiJavaCodeReferenceElement ref = psiElementFactory.createReferenceElementByFQClassName("java.io.Serializable", GlobalSearchScope.allScope(project));
        target.getImplementsList().add(ref);
    }
}
