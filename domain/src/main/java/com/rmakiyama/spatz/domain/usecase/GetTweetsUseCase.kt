package com.rmakiyama.spatz.domain.usecase

import com.rmakiyama.spatz.domain.model.tweet.Tweet

interface GetTweetsUseCase : UseCase<Unit, List<Tweet>>
