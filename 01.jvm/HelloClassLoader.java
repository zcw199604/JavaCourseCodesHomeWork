package zcw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) throws Exception{

        HelloClassLoader helloWord = new HelloClassLoader();
        Object o = helloWord.findClass("Hello").newInstance();
        // 运行hello方法
        final String methodName = "hello";
        Method declaredMethod = o.getClass().getDeclaredMethod(methodName);
        declaredMethod.invoke(o);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream fileInputStream = null;
        try {
            File file = new File("E:\\workspace-idea\\learn\\java\\zcw\\Hello\\Hello.xlass");
            fileInputStream = new FileInputStream(file);
            byte[] bytes = fileInputStream.readAllBytes();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte)(255 - bytes[i]);
            }
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
