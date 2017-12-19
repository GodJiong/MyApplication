
cmake_minimum_required(VERSION 3.4.1) #指定需要CMAKE的最小版本

add_library(
             HelloWorld  #这个地方可以自己命名生成的so库，不设置默认是palmread-lib  so文件：lib+moduleName+.so

             SHARED

             src/main/cpp/wj-lib.c ) #添加的库的路径（可以在上面重新命名此库）

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

target_link_libraries( # Specifies the target library.
                       HelloWorld #这个名字要和add_library中的命名一致，否则会找不到

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )