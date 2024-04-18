package com.example.atd_plugin.language;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MyDebuggerSettingsEditor extends SettingsEditor<MyDebuggerRunConfiguration> {
    private final JPanel myPanel;
    private final JBTextField myScriptPathField;

    public MyDebuggerSettingsEditor() {
        myPanel = new JPanel();
        myScriptPathField = new JBTextField();
        myPanel.add(LabeledComponent.create(myScriptPathField, "Script Path:"));
    }

    @Override
    protected void resetEditorFrom(@NotNull MyDebuggerRunConfiguration configuration) {
        myScriptPathField.setText(configuration.getScriptPath());
    }

    @Override
    protected void applyEditorTo(@NotNull MyDebuggerRunConfiguration configuration) {
        configuration.setScriptPath(myScriptPathField.getText());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return myPanel;
    }
}
