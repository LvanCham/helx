package com.cham.alice.entity

/**
 * Hello World
 * Date: 2019/12/31
 * Author: Cham
 */
 class HomeListEntity{
    var apkLink: String=""
    var audit: Int=0
    var author: String=""
    get() {
       return  if(field.isNotEmpty()){
           field
        }else{
            "查无此人"
        }
    }
    var chapterId: Int=0
    var chapterName: String=""
    var collect: Boolean=false
    var courseId: Int=0
    var desc: String=""
    var envelopePic: String=""
    var fresh: Boolean=false
    var id: Int=0
    var link: String=""
    var niceDate: String=""
    var niceShareDate: String=""
    var origin: String=""
    var prefix: String=""
    var projectLink: String=""
    var publishTime: String=""
    var selfVisible: Int=0
    var shareDate: Long=0
    var shareUser: String=""
    var superChapterId: Int=0
    var superChapterName: String=""
    var tags: List<Tag> = mutableListOf()
    var title: String=""
    var type: Int=0
    var userId: Int=0
    var visible: Int=0
    var zan: Int =0
}



data class Tag(
        val name: String,
        val url: String
)