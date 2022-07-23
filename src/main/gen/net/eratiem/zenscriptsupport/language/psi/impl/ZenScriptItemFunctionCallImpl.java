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

public class ZenScriptItemFunctionCallImpl extends ASTWrapperPsiElement implements ZenScriptItemFunctionCall {

  public ZenScriptItemFunctionCallImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZenScriptVisitor visitor) {
    visitor.visitItemFunctionCall(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZenScriptVisitor) accept((ZenScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ZenScriptFunctionCall getFunctionCall() {
    return findNotNullChildByClass(ZenScriptFunctionCall.class);
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

}
