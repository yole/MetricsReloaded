/*
 * Copyright 2005, Sixth and Red River Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.sixrr.stockmetrics.interfaceCalculators;

import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.ClassInheritorsSearch;
import com.intellij.util.Query;

public class NumImplementationsCalculator extends InterfaceCalculator {

    protected PsiElementVisitor createVisitor() {
        return new Visitor();
    }

    private class Visitor extends JavaRecursiveElementVisitor {

        public void visitClass(final PsiClass aClass) {
            super.visitClass(aClass);
            if (isInterface(aClass)) {
                final Runnable runnable = new Runnable() {
                    public void run() {
                        final Project project = executionContext.getProject();
                        final GlobalSearchScope globalScope = GlobalSearchScope.projectScope(project);
                        final Query<PsiClass> query =
                                ClassInheritorsSearch.search(aClass,
                                        globalScope, true, true, true);
                        int numImplementations = 0;
                        for (final PsiClass inheritor : query) {
                            if (!inheritor.isInterface()) {
                                numImplementations++;
                            }
                        }
                        postMetric(aClass, numImplementations);
                    }
                };
                final ProgressManager progressManager = ProgressManager.getInstance();
                progressManager.runProcess(runnable, null);
            }
        }
    }
}
