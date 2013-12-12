<script type="text/javascript">
    var groupNum = ${groupNum};

    if (0 != groupNum) {
        $('#groupNameError').hide();
        $('#groupNumError').show();
        $('#groupNum').attr("value", groupNum);
    } else {
        $('#groupNameError').hide();
        $('#groupNumError').hide();
        $('#groupNum').attr("value", 0);
    }
</script>