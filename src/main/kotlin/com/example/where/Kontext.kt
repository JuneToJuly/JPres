package com.example.where

import com.intellij.ide.util.EditSourceUtil
import com.intellij.ide.util.PsiNavigationSupport
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.event.CaretEvent
import com.intellij.openapi.editor.event.CaretListener
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.fileEditor.impl.FileEditorManagerImpl
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiReferenceExpression
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.PsiNavigateUtil


class Kontext : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val manager = event.project?.let { FileEditorManager.getInstance(it) }
        val windowMan = event.project?.let { FileEditorManagerEx.getInstanceEx(it) }


        EditorFactory.getInstance().eventMulticaster.addCaretListener(object : CaretListener {
            override fun caretPositionChanged(e: CaretEvent) {

                val psiFile = event?.project?.let {
                    manager?.selectedTextEditor?.document?.let { it1 ->
                        PsiDocumentManager.getInstance(it).getPsiFile(it1)
                    }
                }


                println(e.caret?.offset)
                psiFile?.findElementAt(e.caret?.offset ?: 0)?.let {
                    if (manager is FileEditorManagerEx) {
                        val fm = manager as FileEditorManagerImpl

                        val parentOfType = PsiTreeUtil.getParentOfType(it, PsiReferenceExpression::class.java)
                        val reference = it.parent.reference?.resolve()
                        if (reference?.navigationElement is Navigatable) {
                            val parentNav = reference?.navigationElement as Navigatable
//                        println("parentNav")
//                        manager?.openFile(reference.containingFile.virtualFile, false);
                            parentNav.navigate(false)
                            println("Resolved")
                        }
                    }
                }
            }
        })

//        EditorFactory.getInstance().eventMulticaster.addCaretListener(object : CaretListener {
//            override fun caretPositionChanged(e: CaretEvent) {
//
//                manager?.selectedTextEditor?.foldingModel?.runBatchFoldingOperation ({
//                    manager?.selectedTextEditor?.foldingModel?.allFoldRegions?.forEach {
//                        if(it.placeholderText.equals("")) {
//                            if (e.caret?.offset != null) {
//                                println(e.caret?.offset)
//                                val carretLine = it.document.getLineNumber(e.caret?.offset ?: 0)
//                                val foldLine = it.document.getLineNumber(it.startOffset)
//                                if (it.isExpanded && (carretLine != foldLine)) {
//                                    it.isExpanded = false
//                                }
//                                else if(!it.isExpanded && (carretLine == foldLine)) {
//                                    it.isExpanded = true
//                                }
//                            }
//                        }
//                    }
//                }, false, true)
//
//                manager?.selectedTextEditor?.foldingModel?.let {
//                    print(it.isOffsetCollapsed(e.caret?.offset ?: 0))
//                }
//            }
//        })
    }
}