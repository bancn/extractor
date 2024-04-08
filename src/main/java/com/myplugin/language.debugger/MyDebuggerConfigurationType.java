package com.example.atd_plugin.language;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.icons.AllIcons;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MyDebuggerConfigurationType implements ConfigurationType {
    @Override
    public String getDisplayName() {
        return "My Debugger";
    }

    @Override
    public String getConfigurationTypeDescription() {
        return "My Debugger Configuration Type";
    }

    @Override
    public Icon getIcon() {
        return AllIcons.Actions.StartDebugger;
    }

    @NotNull
    @Override
    public String getId() {
        return "MY_DEBUGGER_CONFIGURATION_TYPE";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{new MyDebuggerConfigurationFactory(this)};
    }
}
