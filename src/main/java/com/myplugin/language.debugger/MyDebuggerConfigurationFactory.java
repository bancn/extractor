package com.example.atd_plugin.language;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;

public class MyDebuggerConfigurationFactory extends ConfigurationFactory {
    protected MyDebuggerConfigurationFactory(ConfigurationType type) {
        super(type);
    }
    @Override
    public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new MyDebuggerRunConfiguration(project, this, "My Debugger");
    }

    @Override
    public boolean isApplicable(@NotNull Project project) {
        // 检查项目中是否存在.atd文件，或者根据其他逻辑确定是否应用你的配置
        return FileTypeIndex.containsFileOfType(AtdFileType.INSTANCE, GlobalSearchScope.allScope(project));
    }
}
