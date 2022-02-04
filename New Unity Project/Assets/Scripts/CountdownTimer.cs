using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.Threading;

public class CountdownTimer : MonoBehaviour
{

    public int timeLeft; //Seconds Overall
    public Text countdown; // UI text object

    private void Start()
    {
        StartCoroutine("LoseTime");
        Time.timeScale = 1;
    }

    private void Update()
    {
        countdown.text = (" " + timeLeft);
    }

    IEnumerator LoseTime()
    {
        while (true)
        {
            yield return new WaitForSeconds(1);
            timeLeft++;
        }
    }
}
