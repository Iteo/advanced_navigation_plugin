package com.iteo.android_navigation.notifiactions

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.iteo.advanced_navigation_plugin.NestedDeepLink
import com.iteo.android_navigation.R
import com.iteo.android_navigation.item_details.ItemDetailsFragmentArgs
import kotlinx.android.synthetic.main.fragment_notifications.detailsNotificationButton
import kotlinx.android.synthetic.main.fragment_notifications.notificationButton
import javax.inject.Inject

class NotificationsFragment @Inject constructor() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel(getString(R.string.default_channel_id), getString(R.string.default_channel_name), importance)
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        val notificationManager = NotificationManagerCompat.from(requireContext())
        notificationManager.createNotificationChannel(mChannel)

        notificationButton.setOnClickListener {

            val nestedDeepLink = NestedDeepLink(
                mainGraphId = R.navigation.main_nav_graph,
                mainEntryDestination = R.id.entryFragment,
                nestedGraphId = R.navigation.filtered_list_nav_graph,
                nestedDestination = R.id.filters_nav_graph
            )

            val not = NotificationCompat.Builder(requireContext(), mChannel.id)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Test")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(nestedDeepLink.toPendingIntent(requireContext()))
                .build()

            with(NotificationManagerCompat.from(requireContext())) {
                notify(1, not)
            }
        }

        detailsNotificationButton.setOnClickListener {

            val nestedDeepLink = NestedDeepLink(
                mainGraphId = R.navigation.main_nav_graph,
                mainEntryDestination = R.id.entryFragment,
                nestedGraphId = R.navigation.filtered_list_nav_graph,
                nestedDestination = R.id.detailsFragment,
                nestedDestinationBundle = ItemDetailsFragmentArgs(1).toBundle()
            )

            val not = NotificationCompat.Builder(requireContext(), mChannel.id)
                .setSmallIcon(R.drawable.ic_airplane)
                .setContentTitle("Title")
                .setContentText("Test")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(nestedDeepLink.toPendingIntent(requireContext()))
                .build()

            with(NotificationManagerCompat.from(requireContext())) {
                notify(1, not)
            }
        }
    }
}
