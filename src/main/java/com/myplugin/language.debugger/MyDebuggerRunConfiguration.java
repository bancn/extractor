package com.example.atd_plugin.language;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationBase;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MyDebuggerRunConfiguration extends RunConfigurationBase {
    protected MyDebuggerRunConfiguration(@NotNull Project project, @NotNull ConfigurationFactory factory, String name) {
        super(project, factory, name);
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new MyDebuggerSettingsEditor();
    }

    @Override
    public void checkConfiguration() {
        // 验证配置的有效性
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
        // 返回用于执行调试会话的状态对象
        return null;
    }

    public String getScriptPath() {
        return "a";
    }

    public void setScriptPath(String scriptPath) {
        // 设置脚本路径

    }
}
