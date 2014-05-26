<?php
/**
 * @file
 * demoiselle_config.features.inc
 */

/**
 * Implements hook_ctools_plugin_api().
 */
function demoiselle_config_ctools_plugin_api($module = NULL, $api = NULL) {
  if ($module == "strongarm" && $api == "strongarm") {
    return array("version" => "1");
  }
}

/**
 * Implements hook_views_default_views_alter().
 */
function demoiselle_config_views_default_views_alter(&$data) {
  if (isset($data['frontpage'])) {
    $data['frontpage']->display['default']->display_options['filters']['language'] = array(
      'id' => 'language',
      'table' => 'node',
      'field' => 'language',
      'value' => array(
        '***CURRENT_LANGUAGE***' => '***CURRENT_LANGUAGE***',
      ),
    ); /* WAS: '' */
    $data['frontpage']->display['default']->display_options['use_more_always'] = FALSE; /* WAS: '' */
    $data['frontpage']->display['default']->display_options['use_more_text'] = 'mais'; /* WAS: '' */
    $data['frontpage']->display['feed']->display_options['title'] = 'Feed da página inicial'; /* WAS: 'Front page feed' */
    unset($data['frontpage']->display['default']->display_options['row_options']);
  }
}
