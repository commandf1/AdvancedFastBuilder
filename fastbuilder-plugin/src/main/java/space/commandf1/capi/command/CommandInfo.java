package space.commandf1.capi.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandInfo {
    String name();
    String description() default "";
    String[] aliases() default {};
    boolean needPermission() default false;
    String permission() default "";
    String noPermissionMessage() default "";
    boolean playerOnly() default false;
    String notPlayerMessage() default "";
}
