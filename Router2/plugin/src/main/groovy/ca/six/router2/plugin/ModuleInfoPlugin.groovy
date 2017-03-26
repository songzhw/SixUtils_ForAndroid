package ca.six.router2.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.internal.api.ApplicationVariantImpl
import com.android.build.gradle.internal.api.LibraryVariantImpl
import com.android.utils.FileUtils
import org.gradle.api.Plugin
import org.gradle.api.Project

class ModuleInfoPlugin implements Plugin<Project> {
    public static final String APT_ARGUMENT_KEY = "moduleName";

    @Override
    void apply(Project project) {

        // 1. add the dependencies automatically
        Project lib = project.rootProject.findProject("lib")
        Project compiler = project.rootProject.findProject("compiler")
        println "szw Plugin apply() currentProject = ${project.name} -- ${lib&&compiler}"
        if(lib && compiler){
            project.dependencies {
                compile lib
                annotationProcessor compiler
            }
        }
        // todo add remote archieve

        // 2. 创建完所有任务后， 新建一个task， 并让jvmCompile依赖它
              // -- Project 创建完所有任务的有向图后， afterEvaluate则是一个回调
        project.afterEvaluate {
            if(project.plugins.hasPlugin(AppPlugin)) {
                AppExtension appExtension = project.android
                appExtension.applicationVariants.all { ApplicationVariantImpl variant ->
                    // 2.1. get Modules info
                    def modules = project.rootProject.subprojects.findAll {
                        it.plugins.hasPlugin(LibraryPlugin) &&
                                it.plugins.hasPlugin(ModuleInfoPlugin)
                    }
                    def sb = new StringBuilder()
                    modules.each { Project libProj->
                        sb.append(libProj.name)
                        sb.append(",")
                    }
                    sb.append(project.name)

                    // 2.2. generate task
                    def parentFile = FileUtils.join(project.buildDir, "generated","source","router2")
                    def createFileTask = project.tasks.create(
                            "router2_generate_file_${variant.name}", CreateFileTask) {
                        it.baseFile = parentFile
                        it.content = sb.toString()
                    }

                    // 2.3. generate dependency
                    def comp = variant.javaCompile
                    if(comp != null){
                        comp.dependsOn createFileTask
                        comp.source(parentFile)// add generated file to javac source

                        comp.options.compilerArgs.add("-A${APT_ARGUMENT_KEY}=${project.name}")
                    }
                }
            }
            else {
                LibraryExtension libExtension = project.android
                println "szw lib--> ${libExtension.libraryVariants.size()} :  ${project.name}"
                libExtension.libraryVariants.all{LibraryVariantImpl variant ->
                    def comp = variant.javaCompile
                    println "szw lib --> ${variant.name} : ${comp != null}"
                    if(comp != null){
                        comp.options.compilerArgs.add("-A${APT_ARGUMENT_KEY}=${project.name}")
                    }
                }
            }
        }


    }

}