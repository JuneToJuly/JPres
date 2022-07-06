package com.example.where

import com.intellij.credentialStore.createSecureRandom
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.FoldingGroup
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDeclarationStatement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiExpressionStatement
import com.intellij.psi.util.PsiTreeUtil
import kotlin.random.Random

class CleanUpFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        // Initialize the group of folding regions that will expand/collapse together.
        val group: FoldingGroup = FoldingGroup.newGroup("ClenUpFoldingGroup")
        // Initialize the list of folding regions
        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        // Get a collection of the literal expressions in the document below root
        val declarationStatement: Collection<PsiDeclarationStatement> =
            PsiTreeUtil.findChildrenOfType(root, PsiDeclarationStatement::class.java)
        // Evaluate the collection
        for (declaration in declarationStatement) {
            val value = declaration.text
            if (value != null && value.contains("=")) {
                val project: Project = declaration.getProject()
                val key: Int = value.indexOf("=")
                if (value.contains("=")) {
                    // Add a folding descriptor for the literal expression at this node.
                    descriptors.add(
                        FoldingDescriptor(
                            declaration.getNode(),
                            TextRange(
                                declaration.getTextRange().getStartOffset() + key - 1,
                                declaration.getTextRange().getEndOffset()
                            ),
                            FoldingGroup.newGroup("Group:" + createSecureRandom().nextFloat())
                        )
                    )
                }
            }
        }

        val expressionStatement: Collection<PsiExpressionStatement> =
            PsiTreeUtil.findChildrenOfType(root, PsiExpressionStatement::class.java)
        // Evaluate the collection
        for (expression in expressionStatement) {
            val value = expression.text
            if (value != null && value.contains("=")) {
                val project: Project = expression.getProject()
                val key: Int = value.indexOf("=")
                if (value.contains("=")) {
                    // Add a folding descriptor for the literal expression at this node.
                    descriptors.add(
                        FoldingDescriptor(
                            expression.getNode(),
                            TextRange(
                                expression.getTextRange().getStartOffset() + key - 1,
                                expression.getTextRange().getEndOffset()
                           ),
                            FoldingGroup.newGroup("CUP:" + createSecureRandom().nextFloat())
                        )
                    )
                }
            }
        }

        return descriptors.toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        return ""
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return true;
    }
}