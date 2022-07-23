package net.eratiem.zenscriptsupport.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.ParserDefinition.SpaceRequirements
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import net.eratiem.zenscriptsupport.language.parser.ZenScriptParser
import net.eratiem.zenscriptsupport.language.psi.ZenScriptFile
import net.eratiem.zenscriptsupport.language.psi.ZenScriptTypes
import org.jetbrains.annotations.NotNull

class ZenScriptParserDefinition : ParserDefinition {
    companion object {
        val WHITE_SPACES: TokenSet = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS: TokenSet = TokenSet.create(ZenScriptTypes.BLOCK_COMMENT, ZenScriptTypes.LINE_COMMENT)

        val FILE: IFileElementType = IFileElementType(ZenScriptLanguage.INSTANCE)
    }

    @NotNull
    override fun createLexer(project: Project?): Lexer = ZenScriptLexerAdapter()

    @NotNull
    override fun getWhitespaceTokens(): TokenSet = WHITE_SPACES

    @NotNull
    override fun getCommentTokens(): TokenSet = COMMENTS

    @NotNull
    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    @NotNull
    override fun createParser(project: Project?): PsiParser = ZenScriptParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun createFile(viewProvider: FileViewProvider): PsiFile = ZenScriptFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): SpaceRequirements =
        SpaceRequirements.MAY

    @NotNull
    override fun createElement(node: ASTNode?): PsiElement = ZenScriptTypes.Factory.createElement(node)
}