package com.joker.webmvc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
 
public class TestMain {  
	  
    public static void main(String[] args) {  
  
        Class<?> clazz = TestMain.class;  
        try {  
            Method method = clazz.getDeclaredMethod("test", String.class,  
                    int.class);  
            String[] pns = getParameterNamesByAsm5(clazz, method);  
            System.out.print(method.getName() + " : ");  
            for (String parameterName : pns) {  
                System.out.print(parameterName + ' ');  
            }  
        } catch (NoSuchMethodException e) {  
            e.printStackTrace();  
        } catch (SecurityException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static void test(String param1, int param2) {  
        System.out.println(param1 + param2);  
    }  
  
    public static String[] getParameterNamesByAsm5(Class<?> clazz,  
            final Method method) {  
        final Class<?>[] parameterTypes = method.getParameterTypes();  
        if (parameterTypes == null || parameterTypes.length == 0) {  
            return null;  
        }  
        final Type[] types = new Type[parameterTypes.length];  
        for (int i = 0; i < parameterTypes.length; i++) {  
            types[i] = Type.getType(parameterTypes[i]);  
        }  
        final String[] parameterNames = new String[parameterTypes.length];  
  
        String className = clazz.getName();  
        int lastDotIndex = className.lastIndexOf(".");  
        className = className.substring(lastDotIndex + 1) + ".class";  
        InputStream is = clazz.getResourceAsStream(className);  
        try {  
            ClassReader classReader = new ClassReader(is);  
            classReader.accept(new ClassVisitor(Opcodes.ASM4) {  
                @Override  
                public MethodVisitor visitMethod(int access, String name,  
                        String desc, String signature, String[] exceptions) {  
                    // 只处理指定的方法  
                    Type[] argumentTypes = Type.getArgumentTypes(desc);  
                    if (!method.getName().equals(name)  
                            || !Arrays.equals(argumentTypes, types)) {  
                        return super.visitMethod(access, name, desc, signature,  
                                exceptions);  
                    }  
                    return new MethodVisitor(Opcodes.ASM4) {  
                        @Override  
                        public void visitLocalVariable(String name, String desc,  
                                String signature, org.objectweb.asm.Label start,  
                                org.objectweb.asm.Label end, int index) {  
                            // 非静态成员方法的第一个参数是this  
                            if (Modifier.isStatic(method.getModifiers())) {  
                                parameterNames[index] = name;  
                            } else if (index > 0) {  
                                parameterNames[index - 1] = name;  
                            }  
                        }  
                    };  
                }  
            }, 0);  
        } catch (IOException e) {  
        } finally {  
            try {  
                if (is != null) {  
                    is.close();  
                }  
            } catch (Exception e2) {  
            }  
        }  
        return parameterNames;  
    }  
  
}  