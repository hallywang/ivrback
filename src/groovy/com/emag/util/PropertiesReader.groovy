package com.emag.util

import grails.util.Environment
import org.springframework.core.io.ClassPathResource

/**
 * properties 配置文件的读取
 * User: linguangfa
 * Date: 12-4-28
 * Time: 下午2:17
 */
class PropertiesReader {
    /** 需要加载的配置文件列表 */
    private static final List<String> propertiesFiles = ['conf/gameurl','conf/ledou']

    /** 存储各配置文件的属性 */
    private static final Map<String, Properties> propertiesMap = new HashMap<String, Properties>()

    /** 有序环境列表 */
    private static final List<String> envs = ['DEVELOPMENT', 'TEST', 'PRODUCTION']
    
    static {
        initProperties()
    }

    /**
     * 初始化配置文件
     * 仅仅在程序启动和job运行时加载
     */
    public static void initProperties() {
        String envName = Environment.getCurrent().getName().toUpperCase()
        println "------Environment is $envName"

        // 当前环境所在“位置”
        int envIndex = 0
        for (int i = 0; i < envs.size(); i++) {
            if (envs[i] == envName) {
                envIndex = i
                break
            }
        }

        propertiesFiles.each {fileName ->
            String fileNameWithEnv = ''
            ClassPathResource resource = null

            for (int i = envIndex; i < envs.size(); i++) {
                fileNameWithEnv = fileName
                fileNameWithEnv = fileName + (i == 2 ? '' : ".${envs[i]}") // 商用环境的配置文件是没有后缀的
                fileNameWithEnv += '.properties'
                
                resource = new ClassPathResource("/${fileNameWithEnv}")
                if (resource.exists()) {
                    // 该环境对应的配置文件存在，则说明找到配置文件
                    println "------load configuration file:${fileNameWithEnv}"
                    break
                }
            }

            if (!resource.exists())
                throw new RuntimeException("not found configuration file:" + fileName)

            // 获取配置
            Properties properties = propertiesMap.get(fileName)
            if (!properties) {
                properties = new Properties()
                propertiesMap.put(fileName, properties)
            }
            InputStream ins = resource.getInputStream()
            properties.clear()
            properties.load(ins)
            ins.close()
        }
    }

    /**
     * 读取配置
     * @param fileName 配置文件名
     * @param key 配置项的key
     * @return 配置项的value
     */
    public static String getValue(String fileName, String key) {
        return propertiesMap.get(fileName)?.getProperty(key)
    }

    /**
     * 读取配置
     * @param fileName 配置文件名
     * @param key 配置项的key
     * @param defaultValue 当配置不存在时的默认返回值
     * @return 配置项的value
     */
    public static String getValue(String fileName, String key, String defaultValue) {
        def value = propertiesMap.get(fileName)?.getProperty(key)
        return value ?: defaultValue
    }



    public static void main(String[] args) {
        println getValue('conf/gameurl', 'huawei_ota_url')
        println getValue('conf/ledou', 'ledou_account')
    }
}
