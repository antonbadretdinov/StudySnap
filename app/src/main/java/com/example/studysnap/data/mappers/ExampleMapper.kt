package com.example.studysnap.data.mappers

import com.example.studysnap.data.models.ExampleModel

fun ExampleModel.toDomain(): com.example.studysnap.domain.models.ExampleModel =
    com.example.studysnap.domain.models.ExampleModel(id = id)