package io.cheesecakelabs.floatingactionmenu.uicomponents.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.cheesecakelabs.core.utils.extensions.getContextCompatDrawable
import io.cheesecakelabs.core.utils.extensions.gone
import io.cheesecakelabs.core.utils.extensions.visible
import io.cheesecakelabs.core.utils.helpers.DimensionsHelper
import io.cheesecakelabs.floatingactionmenu.R
import io.cheesecakelabs.floatingactionmenu.utils.extensions.setColors
import io.cheesecakelabs.floatingactionmenu.utils.extensions.setForegroundDrawable
import io.cheesecakelabs.floatingactionmenu.utils.extensions.setupListener
import io.cheesecakelabs.floatingactionmenu.utils.listeners.FloatingActionMenuListener
import kotlinx.android.synthetic.main.fab_menu_button.view.*

open class FloatingActionMenuView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {

    data class FloatingMenuItem(val position: Int, val drawable: Int)

    private var isFabMenuOpened = false
    private var isAnimating = false

    private var listener: FloatingActionMenuListener? = null
    private var floatingButtons = mutableListOf<FloatingActionButton>()

    private var distanceBetweenItems = 5f
    private var backgroundColorDefault = android.R.color.white
    private var foregroundColorDefault = android.R.color.black
    private var backgroundColorHighlight = android.R.color.black
    private var foregroundColorHighlight = android.R.color.white

    private var drawableOnMenuCollapsed = R.drawable.ic_menu
    private var drawableOnMenuExpanded = R.drawable.ic_close

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.FloatingActionMenuView,
                0,
                0
            )

            drawableOnMenuCollapsed = typedArray.getResourceId(
                R.styleable.FloatingActionMenuView_drawableOnMenuCollapsed,
                R.drawable.ic_menu
            )

            drawableOnMenuExpanded = typedArray.getResourceId(
                R.styleable.FloatingActionMenuView_drawableOnMenuExpanded,
                R.drawable.ic_close
            )

            backgroundColorDefault = typedArray.getResourceId(
                R.styleable.FloatingActionMenuView_backgroundColorDefault,
                0
            )
            foregroundColorDefault = typedArray.getResourceId(
                R.styleable.FloatingActionMenuView_foregroundColorDefault,
                0
            )
            backgroundColorHighlight = typedArray.getResourceId(
                R.styleable.FloatingActionMenuView_backgroundColorHighlight,
                0
            )
            foregroundColorHighlight = typedArray.getResourceId(
                R.styleable.FloatingActionMenuView_foregroundColorHighlight,
                0
            )

            distanceBetweenItems = DimensionsHelper.dpToFloat(
                typedArray.getDimension(
                    R.styleable.FloatingActionMenuView_distanceBetweenItems,
                    5f
                ),
                resources.displayMetrics
            )

            setupMenuButton()

            typedArray.recycle()
        }
    }

    fun setupMenuItems(
        floatingMenuItems: MutableList<FloatingMenuItem>,
        listener: FloatingActionMenuListener
    ) {
        this.listener = listener
        onMenuItemsSet(floatingMenuItems)
    }

    fun setColorDefault() {
        menuFloatingButton.setColors(context, backgroundColorDefault, foregroundColorDefault)

        floatingButtons.forEach { fab ->
            fab.setColors(context, backgroundColorDefault, foregroundColorDefault)
        }
    }

    fun setColorHighlight() {
        menuFloatingButton.setColors(context, backgroundColorHighlight, foregroundColorHighlight)

        floatingButtons.forEach { fab ->
            fab.setColors(context, backgroundColorHighlight, foregroundColorHighlight)
        }
    }

    fun collapseMenu() {
        onFabMenuButtonClicked()
    }

    private fun setupMenuButton() {
        val view = LayoutInflater.from(context).inflate(R.layout.fab_menu_button, this, false)
        val floatBtn = view.findViewById<FloatingActionButton>(R.id.menuFloatingButton)

        floatBtn?.run {
            setForegroundDrawable(context.getContextCompatDrawable(drawableOnMenuCollapsed))
            setOnClickListener { onFabMenuButtonClicked() }
        }

        this.addView(view)
    }

    private fun onFabMenuButtonClicked() {
        if (!isAnimating) {
            isAnimating = true

            if (isFabMenuOpened) hideFabMenu()
            else showFabMenu()

            isFabMenuOpened = !isFabMenuOpened
        }
    }

    private fun onMenuItemsSet(floatingMenuItems: MutableList<FloatingMenuItem>) {
        floatingMenuItems.forEach { floatingMenuItem ->
            val view = LayoutInflater.from(context).inflate(
                R.layout.fab_menu_item,
                this,
                false
            )
            val floatBtn =
                view.findViewById<FloatingActionButton>(R.id.menuItemFloatingButton)

            floatBtn?.run {
                setForegroundDrawable(context.getContextCompatDrawable(floatingMenuItem.drawable))
                floatingButtons.add(this)
                setOnClickListener { listener?.onMenuItemClicked(floatingMenuItem.position) }
            }

            this.addView(view)
        }

        menuFloatingButton.bringToFront()
        setColorDefault()
    }

    private fun showFabMenu() {
        val initialDistance = distanceBetweenItems + menuFloatingButton.height

        floatingButtons.forEachIndexed { index, fab ->
            fab.visible()

            val distance = initialDistance + (fab.height + distanceBetweenItems) * index

            val viewPropertyAnimator = fab.animate().translationY(-distance)

            if (fab == floatingButtons.last()) {
                viewPropertyAnimator.setupListener(
                    onAnimationStart = {
                        listener?.onMenuExpanded()
                        menuFloatingButton.setForegroundDrawable(
                            context.getContextCompatDrawable(drawableOnMenuExpanded)
                        )
                    },
                    onAnimationEnd = { isAnimating = false }
                )
            }
        }
    }

    private fun hideFabMenu() {
        floatingButtons.forEach { fab ->
            val viewPropertyAnimator = fab.animate().translationY(0f)

            if (fab == floatingButtons.last()) {
                viewPropertyAnimator.setupListener(
                    onAnimationStart = {
                        menuFloatingButton.setForegroundDrawable(
                            context.getContextCompatDrawable(drawableOnMenuCollapsed)
                        )
                    },
                    onAnimationEnd = {
                        listener?.onMenuCollapsed()
                        floatingButtons.forEach { it.gone() }
                        isAnimating = false
                    }
                )
            }
        }
    }
}
