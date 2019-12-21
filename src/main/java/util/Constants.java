package util;

public class Constants {

  private static final String DOT = ".";
  private static final String METHOD_REFERENCE_SEPARATOR = "::";
  private static final String INVOKE_LATER_SWING_QUALIFIER = "SwingUtilities";
  private static final String INVOKE_LATER_METHOD_NAME = "invokeLater";

  public static final String INVOKE_LATER_REFERENCE_QUALIFIED_NAME = INVOKE_LATER_SWING_QUALIFIER + METHOD_REFERENCE_SEPARATOR + INVOKE_LATER_METHOD_NAME;

  public static final String INVOKE_LATER_QUALIFIED_NAME = INVOKE_LATER_SWING_QUALIFIER + DOT + INVOKE_LATER_METHOD_NAME;
  public static final String QUICKFIX_INVOKE_LATER_NAME = "Replace with application.invokeLater";
  public static final String QUICKFIX_INVOKE_LATER_REFERENCE_NAME = "Replace with application::invokeLater";
  public static final String QUICKFIX_INVOKE_LATER_METHOD_REPLACE_TEMPLATE = "ApplicationManager.getApplication().invokeLater(a)";
  public static final String QUICKFIX_INVOKE_LATER_METHOD_REFERENCE_REPLACE_TEMPLATE = "ApplicationManager.getApplication::invokeLater";
  public static final String INVOKE_LATER_VISITOR_TEMPLATE = "SwingUtilities#invokeLater usage is forbidden in intellij codebase. Use application#invokeLater";
}
