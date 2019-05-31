var $form1;
var form1;
var $;
layui.define(['jquery', 'form', 'table'], function () {
    $ = layui.jquery;
    form1 = layui.form;
    treeSelect1(defaults1);
});

function treeSelect1(config) {
    $form1 = $(config.form);
    // config.v1 = config.v1 ? config.v1 : 110000;
    // config.v2 = config.v2 ? config.v2 : 110100;
    // config.v3 = config.v3 ? config.v3 : 110101;
    $.each(threeSelectData, function (k, v) {
        appendOptionTo1($form1.find('select[name=' + config.s1 + ']'), k, v.val, config.v1);
    });
    form1.render();
    cityEvent1(config);
    areaEvent1(config);
    form1.on('select(' + config.s1 + ')', function (data) {
        cityEvent1(data);
        form1.on('select(' + config.s2 + ')', function (data) {
            areaEvent1(data);
        });
    });

    function cityEvent1(data) {
        $form1.find('select[name=' + config.s2 + ']').html("");
        config.v1 = data.value ? data.value : config.v1;
        $.each(threeSelectData, function (k, v) {
            if (v.val == config.v1) {
                if (v.items) {
                    appendOptionTo1($form.find('select[name=' + config.s2 + ']'), "请选择", "", config.v2);
                    $.each(v.items, function (kt, vt) {
                        appendOptionTo1($form1.find('select[name=' + config.s2 + ']'), kt, vt.val, config.v2);
                    });
                }
            }
        });
        form1.render();
        config.v2 = $('select[name=' + config.s2 + ']').val();
        areaEvent1(config);
    }

    function areaEvent1(data) {
        $form1.find('select[name=' + config.s3 + ']').html("");
        config.v2 = data.value ? data.value : config.v2;
        $.each(threeSelectData, function (k, v) {
            if (v.val == config.v1) {
                if (v.items) {
                    $.each(v.items, function (kt, vt) {
                        if (vt.val == config.v2) {
                            appendOptionTo1($form.find('select[name=' + config.s3 + ']'), "请选择", "", config.v3);
                            $.each(vt.items, function (ka, va) {
                                appendOptionTo1($form1.find('select[name=' + config.s3 + ']'), ka, va, config.v3);
                            });
                        }
                    });
                }
            }
        });
        form1.render();
        form1.on('select(' + config.s3 + ')', function (data) {
        });
    }

    function appendOptionTo1($o, k, v, d) {
        var $opt = $("<option>").text(k).val(v);
        if (v == d) {
            $opt.attr("selected", "selected")
        }
        $opt.appendTo($o);
    }
}