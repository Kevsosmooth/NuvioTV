package com.nuvio.tv.ui.screens.detail

import com.nuvio.tv.domain.model.Meta

private val upNextIdPrefixes = listOf("mdblist_upnext_", "simkl_upnext_", "pmdb_resume_", "upnext_")
private val imdbIdPattern = Regex("^tt\\d+$")

/**
 * Addon "Up Next" rows (e.g. AIOMetadata's Simkl Up Next) use one-episode ids like
 * `simkl_upnext_tt11540908_S1E6`, whose meta carries only that episode. Returns the
 * show's real id so the details page can link to the full season and episode list.
 */
internal fun resolveFullShowId(meta: Meta): String? {
    val prefix = upNextIdPrefixes.firstOrNull { meta.id.startsWith(it) } ?: return null
    meta.imdbId?.trim()?.takeIf { imdbIdPattern.matches(it) }?.let { return it }
    return meta.id.removePrefix(prefix).substringBeforeLast('_').takeIf { imdbIdPattern.matches(it) }
}
