package com.rmakiyama.spatz.domain.usecase.tweet

import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.domain.usecase.SuspendUseCase

interface GetTweetsUseCase : SuspendUseCase<Unit, List<Tweet>>
