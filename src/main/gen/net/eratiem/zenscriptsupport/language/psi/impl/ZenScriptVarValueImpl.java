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

public class ZenScriptVarValueImpl extends ASTWrapperPsiElement implements ZenScriptVarValue {

  public ZenScriptVarValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZenScriptVisitor visitor) {
    visitor.visitVarValue(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZenScriptVisitor) accept((ZenScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZenScriptArray getArray() {
    return findChildByClass(ZenScriptArray.class);
  }

  @Override
  @Nullable
  public ZenScriptBoolean getBoolean() {
    return findChildByClass(ZenScriptBoolean.class);
  }

  @Override
  @Nullable
  public ZenScriptMcItem getMcItem() {
    return findChildByClass(ZenScriptMcItem.class);
  }

  @Override
  @Nullable
  public ZenScriptOreLiquidEntry getOreLiquidEntry() {
    return findChildByClass(ZenScriptOreLiquidEntry.class);
  }

  @Override
  @Nullable
  public PsiElement getNumber() {
    return findChildByType(NUMBER);
  }

}
