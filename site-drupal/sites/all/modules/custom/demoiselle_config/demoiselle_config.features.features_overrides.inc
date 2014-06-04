<?php
/**
 * @file
 * demoiselle_config.features.features_overrides.inc
 */

/**
 * Implements hook_features_override_default_overrides().
 */
function demoiselle_config_features_override_default_overrides() {
  // This code is only used for UI in features. Exported alters hooks do the magic.
  $overrides = array();

  // Exported overrides for: views_view
  $overrides["views_view.frontpage.display|default|display_options|filters|language"] = array(
    'id' => 'language',
    'table' => 'node',
    'field' => 'language',
    'value' => array(
      '***CURRENT_LANGUAGE***' => '***CURRENT_LANGUAGE***',
    ),
  );
  $overrides["views_view.frontpage.display|default|display_options|row_options"]["DELETED"] = TRUE;
  $overrides["views_view.frontpage.display|default|display_options|use_more_always"] = FALSE;
  $overrides["views_view.frontpage.display|default|display_options|use_more_text"] = 'mais';
  $overrides["views_view.frontpage.display|feed|display_options|title"] = 'Feed da página inicial';

 return $overrides;
}
