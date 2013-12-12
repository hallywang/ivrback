<script type="text/javascript">
    var gkeyN = ${gkeyNum};

    if (0 != gkeyN) {
        $('#keyError').hide();
        $('#gkeyError').show();
        $('#gkeyNum').attr("value", gkeyN);
    } else {
        $('#keyError').hide();
        $('#gkeyError').hide();
        $('#gkeyNum').attr('value', 0)
    }
</script>