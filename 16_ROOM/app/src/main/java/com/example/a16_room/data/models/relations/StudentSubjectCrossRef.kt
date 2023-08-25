package com.example.a16_room.data.models.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.a16_room.data.models.StudentModel
import com.example.a16_room.data.models.SubjectModel

@Entity(
    tableName = "StudentSubjectCrossRef",
    primaryKeys = ["studentId", "subjectId"],
    foreignKeys = [
        ForeignKey(entity = StudentModel::class, parentColumns = ["id"], childColumns = ["studentId"]),
        ForeignKey(entity = SubjectModel::class, parentColumns = ["subject_id"], childColumns = ["subjectId"])
    ]
)
class StudentSubjectCrossRef(
    @ColumnInfo(name = "studentId")
    val studentId: Int,

    @ColumnInfo(name = "subjectId")
    val subjectId: Int
)