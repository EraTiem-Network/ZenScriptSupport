// This is a generated file. Not intended for manual editing.
package net.eratiem.zenscriptsupport.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.eratiem.zenscriptsupport.language.psi.ZenScriptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.eratiem.zenscriptsupport.language.psi.*;

public class ZenScriptVarInitImpl extends ASTWrapperPsiElement implements ZenScriptVarInit {

  public ZenScriptVarInitImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZenScriptVisitor visitor) {
    visitor.visitVarInit(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZenScriptVisitor) accept((ZenScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ZenScriptObject> getObjectList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZenScriptObject.class);
  }

  @Override
  @NotNull
  public ZenScriptVarModifier getVarModifier() {
    return findNotNullChildByClass(ZenScriptVarModifier.class);
  }

  @Override
  @Nullable
  public ZenScriptVarValue getVarValue() {
    return findChildByClass(ZenScriptVarValue.class);
  }

}
