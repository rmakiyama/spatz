package com.rmakiyama.spatz.home.item

import coil.api.load
import com.rmakiyama.spatz.domain.model.tweet.Tweet
import com.rmakiyama.spatz.home.R
import com.rmakiyama.spatz.home.databinding.ItemTweetBinding
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

data class TweetItem(
    val tweet: Tweet,
    val listener: TweetOnClickListener
) : Item<GroupieViewHolder>(tweet.hashCode().toLong()) {

    override fun getLayout(): Int = R.layout.item_tweet

    override fun bind(
        viewHolder: GroupieViewHolder,
        position: Int
    ) {
        val binding = ItemTweetBinding.bind(viewHolder.root)
        binding.userImage.load(tweet.user.profilePhotoUrl.value)
        binding.userName.text = tweet.user.name.value
        binding.userScreenName.text = tweet.user.screenName.value
        binding.tweetText.text = tweet.text.value

        binding.userImage.setOnClickListener { onClickUser() }
        binding.userName.setOnClickListener { onClickUser() }
        binding.userScreenName.setOnClickListener { onClickUser() }
    }

    private fun onClickUser() = listener.onClickUser(tweet)

    interface TweetOnClickListener {
        fun onClickUser(tweet: Tweet)
    }
}
